package kz.alisher.example.wicket.projects.forms.pages;

import kz.alisher.example.wicket.projects.BasePage;
import org.apache.wicket.Page;
import org.apache.wicket.markup.html.link.Link;

/**
 * @author dashorst
 */
public class Page2 extends BasePage {
  public Page2(final Page returnTo) {
    add(new Link("returnLink") {
      @Override
      public void onClick() {
        setResponsePage(returnTo);
      }
    });
  }
}
