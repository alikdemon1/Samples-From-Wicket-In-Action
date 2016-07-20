package kz.alisher.example.wicket.projects.cheesr.pages;

import kz.alisher.example.wicket.projects.BasePage;
import kz.alisher.example.wicket.projects.cheesr.model.Cheese;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;


public class CheeseDetails extends BasePage {

    public CheeseDetails(PageParameters parameters) {

        Cheese cheese = new Cheese();
        cheese.setName(String.valueOf(parameters.get("name")));
        cheese.setDescription(String.valueOf(parameters.get("description")));

        createComponents(cheese);
    }

    private void createComponents(Cheese cheese) {
        add(new Label("name", cheese.getName()));
        add(new Label("description", cheese.getDescription()));
    }
}
