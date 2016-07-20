package kz.alisher.example.wicket.projects.cheesr.auth;

import kz.alisher.example.wicket.projects.BasePage;
import org.apache.wicket.Page;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class SignOutPage extends BasePage {

    public static final String REDIRECTPAGE_PARAM = "redirectpage";

    @SuppressWarnings("unchecked")
    public SignOutPage(final PageParameters parameters) {
        String page = String.valueOf(parameters.get(REDIRECTPAGE_PARAM));
        Class<? extends Page> pageClass;
        if (page != null) {
            try {
                pageClass = (Class<? extends Page>) Class.forName(page);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            pageClass = getApplication().getHomePage();
        }
        getSession().invalidate();
        setResponsePage(pageClass);
    }
}
