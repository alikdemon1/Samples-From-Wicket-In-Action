package kz.alisher.example.wicket.projects.cheesr.auth;

import kz.alisher.example.wicket.projects.ApplicationSession;
import kz.alisher.example.wicket.projects.BasePage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

public class SigninPage extends BasePage {

    private static class SignInForm extends Form<Void> {

        private String wiaPassword;
        private String wiaUsername;

        public SignInForm(final String id) {
            super(id);
            setModel(new CompoundPropertyModel(this));
            add(new TextField("wiaUsername"));
            add(new PasswordTextField("wiaPassword"));
        }

        public String getWiaPassword() {
            return wiaPassword;
        }

        public String getWiaUsername() {
            return wiaUsername;
        }

        @Override
        public final void onSubmit() {
            if (ApplicationSession.get().signIn(wiaUsername, wiaPassword)) {
                continueToOriginalDestination();
                setResponsePage(new kz.alisher.example.wicket.projects.cheesr.pages.Index());
            } else {
                error("Unknown username/ password");
            }
        }

        public void setWiaPassword(String password) {
            this.wiaPassword = password;
        }

        public void setWiaUsername(String username) {
            this.wiaUsername = username;
        }
    }

    public SigninPage() {
        add(new SignInForm("signInForm"));
        add(new FeedbackPanel("feedback"));
    }
}
