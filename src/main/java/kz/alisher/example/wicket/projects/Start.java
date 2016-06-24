package kz.alisher.example.wicket.projects;

import kz.alisher.example.wicket.model.Project;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

/**
 * Created by Alisher on 23.06.2016.
 */
public class Start extends BasePage {

    public Start() {

        add(new ListView("list_app", getProjects()) {

            protected void populateItem(final ListItem listItem) {
                Project p = (Project) listItem.getModelObject();
                listItem.add(new Label("name_app", p.getName()));
                listItem.add(new Link("link_app", listItem.getModel()) {

                    public void onClick() {
                        Project proj = (Project) getModelObject();
                        setResponsePage(proj.getClassName());
                    }
                });
            }
        });

        add(new Link("clear_session"){
            public void onClick() {
                System.out.println("SESSION: " + getCheesrSession().getId());
                getCheesrSession().invalidate();
                System.out.println("AFTER CLEAR: " + getCheesrSession().getId());
            }
        });
    }
}
