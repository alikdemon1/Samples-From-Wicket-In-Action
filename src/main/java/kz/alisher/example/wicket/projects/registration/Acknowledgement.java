package kz.alisher.example.wicket.projects.registration;

import kz.alisher.example.wicket.projects.BasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Created by Alisher on 04.06.2016.
 */
class Acknowledgement extends BasePage {

    Acknowledgement(final PageParameters page) throws Exception {

        Label name = new Label("name", page.get("name"));
        Label email = new Label("email", page.get("email"));
        Label regDate = new Label("createdOn", page.get("createdOn"));

        add(name);
        add(email);
        add(regDate);
    }
}