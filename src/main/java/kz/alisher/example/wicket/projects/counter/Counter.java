package kz.alisher.example.wicket.projects.counter;

import kz.alisher.example.wicket.projects.BasePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.PropertyModel;

/**
 * Created by Alisher on 23.06.2016.
 */
public class Counter extends BasePage {
    private int counter = 0;
    private Label label;

    public Counter() {
        add(new AjaxFallbackLink("link") {

            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                counter++;
                if (ajaxRequestTarget != null) {
                    ajaxRequestTarget.add(label);
                }
            }
        });

        label = new Label("label", new PropertyModel(this, "counter"));
        label.setOutputMarkupId(true);
        add(label);
    }
}
