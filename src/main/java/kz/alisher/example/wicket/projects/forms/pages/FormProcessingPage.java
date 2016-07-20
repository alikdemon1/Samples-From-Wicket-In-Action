package kz.alisher.example.wicket.projects.forms.pages;

import kz.alisher.example.wicket.projects.BasePage;
import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;
import org.apache.wicket.validation.validator.RangeValidator;


public class FormProcessingPage extends BasePage {
    public FormProcessingPage() {
        Form form = new Form("search") {
            @Override
            protected void onSubmit() {
                System.out.println("Form was submitted using " + getMethod());
                // set redirect to false to allow users to bookmark the
                // result page
                // setRedirect();
            }
        };
        add(form);
        form.add(new TextField("q", new Model("")));

        form = new Form("formPost") {
            @Override
            protected void onSubmit() {
                System.out.println("Form was submitted using " + getMethod());
            }
        };
        add(form);
        form.add(new TextField("field", new Model("")));

    /* Section 7.2.2 */
        CompoundPropertyModel model = new CompoundPropertyModel(new ValueMap());
        form = new Form("form", model) {
            @Override
            protected void onSubmit() {
                System.out.println("OK");
            }

            @Override
            protected void onError() {
                System.out.println("Error");
            }

        };
        add(form);

        // add the fields that demonstrate the various stages of form processing
        form.add(new TextField("required", model.bind("value1")).setRequired(true));
        form.add(new TextField("integer", model.bind("value2"), Integer.class).setRequired(true));
        form.add(new TextField("mustbeone", model.bind("value3"), Integer.class).setRequired(true).add(RangeValidator.range(1, 1)));

        // add labels to display the model values
        form.add(new Label("value1"));
        form.add(new Label("value2"));
        form.add(new Label("value3"));

        // add feedback panel to display any feedback messages for this form
        form.add(new FeedbackPanel("feedback", new ContainerFeedbackMessageFilter(form)));
    }
}
