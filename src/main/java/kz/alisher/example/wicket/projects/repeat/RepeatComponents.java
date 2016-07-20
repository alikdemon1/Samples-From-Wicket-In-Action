package kz.alisher.example.wicket.projects.repeat;

import kz.alisher.example.wicket.projects.BasePage;
import org.apache.wicket.Application;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alisher on 27.06.2016.
 */
public class RepeatComponents extends BasePage {

    public RepeatComponents() {
        Application.get().getMarkupSettings().setStripWicketTags(false);
        RepeatingView rv1 = new RepeatingView("rv1");
        add(rv1);
        for (int i = 0; i < 5; i++) {
            rv1.add(new Label(String.valueOf(i), "Value " + i));
        }

        RepeatingView rv2 = new RepeatingView("rv2");
        add(rv2);
        rv2.add(new Label(rv2.newChildId(), "Label with text"));
        rv2.add(new Link(rv2.newChildId()) {
            public void onClick() {
            }
        });

        List<MenuItem> menu = createMenu();

        RepeatingView rv = new RepeatingView("menu");
        add(rv);
        for (MenuItem item : menu) {
            WebMarkupContainer parent = new WebMarkupContainer(rv.newChildId());
            rv.add(parent);
            BookmarkablePageLink link = new BookmarkablePageLink("link", item.getDestination());
            parent.add(link);
            link.add(new Label("caption", item.getCaption()));
        }

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }


        ListView lv1 = new ListView("lv1", list) {
            protected void populateItem(ListItem item) {
                item.add(new Label("value", "Value " + item.getModelObject()));
            }
        };
        add(lv1);

        ListView lv2 = new ListView("lv2", menu) {
            protected void populateItem(ListItem item) {
                MenuItem menuitem = (MenuItem) item.getModelObject();
                BookmarkablePageLink link = new BookmarkablePageLink("link", menuitem.getDestination());
                link.add(new Label("caption", menuitem.getCaption()));
                item.add(link);
            }
        };
        add(lv2);

        add(new Link("add", new Model((Serializable) menu)) {
            public void onClick() {
                List<MenuItem> menu = (List<MenuItem>) getModelObject();
                MenuItem item = new MenuItem();
                item.setCaption("Menu " + (menu.size() + 1));
                item.setDestination(Page1.class);
                menu.add(item);
            }
        });

        add(new Link("remove", new Model((Serializable) menu)) {
            public void onClick() {
                List<MenuItem> menu = (List<MenuItem>) getModelObject();
                menu.remove(0);
            }
        });
    }

    private List<MenuItem> createMenu() {
        List<MenuItem> menu = new ArrayList<MenuItem>();

        MenuItem item = new MenuItem();
        item.setCaption("Home");
        item.setDestination(Page1.class);
        menu.add(item);

        item = new MenuItem();
        item.setCaption("Cheeses");
        item.setDestination(Page1.class);
        menu.add(item);

        item = new MenuItem();
        item.setCaption("Wines");
        item.setDestination(Page1.class);
        menu.add(item);

        item = new MenuItem();
        item.setCaption("Recipes");
        item.setDestination(Page1.class);
        menu.add(item);

        return menu;
    }
}
