package kz.alisher.example.wicket.projects.forms.pages;

import kz.alisher.example.wicket.projects.BasePage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

/**
 * Created by Alisher on 27.06.2016.
 */
public class Form1 extends BasePage {
    public Form1() {
        Form form = new Form("form") {
            @Override
            protected void onSubmit() {
                System.out.println("form was submitted!");
            }
        };
        add(form);
        form.add(new TextField("field", new Model("")));
    }
}
