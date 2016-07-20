package kz.alisher.example.wicket.projects.cheesr.pages;

import kz.alisher.example.wicket.projects.cheesr.model.Cart;
import kz.alisher.example.wicket.projects.cheesr.model.Cheese;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;

public class ShoppingCartPanel extends Panel {
    private Cart cart;

    public ShoppingCartPanel(String id, final Cart cart, final boolean flag) {
        super(id);
        this.cart = cart;
        setOutputMarkupId(true);

        add(new ListView(id, new PropertyModel(this, "cart.cheeses")) {
            @Override
            protected void populateItem(ListItem item) {
                Cheese cheese = (Cheese) item.getModelObject();
                item.add(new Label("name", cheese.getName()));
                item.add(new Label("price", "$" + cheese.getPrice()));
                item.add(new Link("remove", item.getModel()) {

                    public void onClick() {
                        Cheese selected = (Cheese) getModelObject();
                        getCart().getCheeses().remove(selected);
                    }
                });
            }
        });

        add(new Label("total", new Model() {
            @Override
            public Serializable getObject() {
                return getCart().getTotal() + "$";
            }
        }));

        add(new Link("checkout") {
            @Override
            public void onClick() {
                setResponsePage(new Checkout());
            }

            @Override
            public boolean isVisible() {
                return !cart.getCheeses().isEmpty() && flag;
            }
        });
    }

    private Cart getCart() {
        return cart;
    }
}

