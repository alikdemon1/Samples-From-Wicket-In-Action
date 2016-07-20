package kz.alisher.example.wicket.projects.cheesr.pages;

import kz.alisher.example.wicket.projects.cheesr.model.Cart;
import kz.alisher.example.wicket.projects.cheesr.model.Cheese;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.util.List;

/**
 * Created by Alisher on 05.07.2016.
 */
public class CheesesPanel extends Panel {


    public CheesesPanel(String id, List<Cheese> cheeseList, final Cart cart, final ShoppingCartPanel shoppingCartPanel) {
        super(id);

        PageableListView cheeses = new PageableListView("cheeses", cheeseList, 5) {

            @Override
            protected void populateItem(ListItem item) {
                Cheese cheese = (Cheese) item.getModelObject();
                PageParameters parameters = new PageParameters();
                parameters.add("name", cheese.getName());
                parameters.add("description", cheese.getDescription());

                item.add(new Label("name", cheese.getName()));
                item.add(new Label("description", cheese.getDescription()));
                item.add(new Label("price", "$" + cheese.getPrice()));
                item.add(new BookmarkablePageLink("more", CheeseDetails.class, parameters));

                item.add(new AjaxFallbackLink("add", item.getModel()) {

                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        Cheese selected = (Cheese) getModelObject();
                        cart.getCheeses().add(selected);
                        if (target != null) {
                            target.add(shoppingCartPanel);
                        }
                    }
                });
            }
        };

        add(cheeses);
        add(new PagingNavigator("navigator", cheeses));
    }
}
