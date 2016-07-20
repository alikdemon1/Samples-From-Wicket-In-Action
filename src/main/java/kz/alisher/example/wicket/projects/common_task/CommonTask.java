package kz.alisher.example.wicket.projects.common_task;

import kz.alisher.example.wicket.projects.BasePage;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;

import java.util.Calendar;

/**
 * Created by Alisher on 27.06.2016.
 */
public class CommonTask extends BasePage {

    public CommonTask() {
        createExample5_6_1();
        createExample5_6_2();
        createExample5_6_3();
    }

    private void createExample5_6_1() {
        // use a webmarkupcontainer to isolate the examples
        WebMarkupContainer example561 = new WebMarkupContainer("examples-5.6.1");

        Label label1 = new Label("label1", "label 1");
        example561.add(label1.setVisible(false));
        example561.add(new Label("label2", "label 2").setVisible(!label1.isVisible()));

        example561.add(new Label("label", "Now I'm visible!") {
            @Override
            public boolean isVisible() {
                int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                return day != Calendar.SATURDAY && day != Calendar.SUNDAY;
            }
        });

        add(example561);
    }

    private void createExample5_6_2() {
        // use a webmarkupcontainer to isolate the examples
        WebMarkupContainer example562 = new WebMarkupContainer("examples-5.6.2");
        example562.add(new Label("message1", "Hello, World!") {
            @Override
            protected void onComponentTag(ComponentTag tag) {
                super.onComponentTag(tag);
                tag.put("style", "color:red");
            }
        });
        example562.add(new Label("message2", "Some text").add(new AttributeModifier("style", "color:red")));

        Link link = new Link("link") {
            public void onClick() {
                System.out.println("Clicked!");
            }
        };
        example562.add(link);
        link.add(new AttributeModifier("onclick", "return confirm('Are you sure?');"));
        add(example562);
    }

    private void createExample5_6_3() {
        // use a webmarkupcontainer to isolate the examples
        WebMarkupContainer example562 = new WebMarkupContainer("examples-5.6.3");
        add(example562);

        example562.add(new Label("message", "Hello, World!").setRenderBodyOnly(true));
    }
}
