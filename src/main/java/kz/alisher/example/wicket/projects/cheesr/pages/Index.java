package kz.alisher.example.wicket.projects.cheesr.pages;

import kz.alisher.example.wicket.projects.BasePage;
import kz.alisher.example.wicket.projects.cheesr.model.Cheese;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;

/**
 * Created by Alisher on 23.06.2016.
 */
public class Index extends BasePage {

    public Index() {
        PageableListView cheeses = new PageableListView("cheeses", getCheeses(), 5) {

            @Override
            protected void populateItem(ListItem item) {
                Cheese cheese = (Cheese) item.getModelObject();
                item.add(new Label("name", cheese.getName()));
                item.add(new Label("description", cheese.getDescription()));
                item.add(new Label("price", "$" + cheese.getPrice()));

                item.add(new Link("add", item.getModel()) {

                    @Override
                    public void onClick() {
                        Cheese selected = (Cheese) getModelObject();
                        getCart().getCheeses().add(selected);
                    }
                });
            }
        };

        add(cheeses);
        add(new PagingNavigator("navigator", cheeses));
        add(new ListView("cart", new PropertyModel(this, "cart.cheeses")) {

            @Override
            protected void populateItem(ListItem item) {
                Cheese cheese = (Cheese) item.getModelObject();
                item.add(new Label("name", cheese.getName()));
                item.add(new Label("price", "$" + cheese.getPrice()));

                item.add(new Link("remove", item.getModel()) {

                    @Override
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
                return !getCart().getCheeses().isEmpty();
            }
        });

    }
}
