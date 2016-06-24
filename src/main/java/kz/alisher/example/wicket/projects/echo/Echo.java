package kz.alisher.example.wicket.projects.echo;

import kz.alisher.example.wicket.projects.BasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

/**
 * Created by Alisher on 23.06.2016.
 */
public class Echo extends BasePage {

    private String message = "type your message";

    public Echo() {
        PropertyModel messageModel = new PropertyModel(this, "message");
        add(new Label("message", messageModel));
        Form<?> form = new Form("form");
        form.add(new TextField("field", messageModel));
        add(form);
    }
}