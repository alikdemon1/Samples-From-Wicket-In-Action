package kz.alisher.example.wicket.projects.cheesr.pages;

import kz.alisher.example.wicket.projects.cheesr.model.Cart;
import kz.alisher.example.wicket.projects.cheesr.model.Cheese;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;

public class ShoppingCartPanel extends Panel {
  private Cart cart;
  public ShoppingCartPanel(String id, Cart cart) {
     super(id);
     this.cart = cart;
     add(new ListView("cart", new PropertyModel(this, "cart.cheeses")) { 
       @Override
       protected void populateItem(ListItem item) {
           Cheese cheese = (Cheese) item.getModelObject();
           item.add(new Label("name", cheese.getName()));
           item.add(new Label("price", "$" + cheese.getPrice()));

           item.add(removeLink("remove", item));
       }
   });
   add(new Label("total", new Model() {
       @Override
       public Serializable getObject() {
           return getCart().getTotal() + "$";
       }
   }));
  }

  private Cart getCart() {
     return cart;
  }
}

