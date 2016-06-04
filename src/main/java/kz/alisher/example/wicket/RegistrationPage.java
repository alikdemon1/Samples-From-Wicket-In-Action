package kz.alisher.example.wicket;

import kz.alisher.example.wicket.Dao.UserDao;
import kz.alisher.example.wicket.model.User;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * Created by Alisher on 04.06.2016.
 */
public class RegistrationPage extends WebPage {
    Form<?> form = null;
    User user = new User();

    @SpringBean
    UserDao dao;

    public RegistrationPage(final PageParameters parameters) {
        super();

        TextField name = new TextField("name", new PropertyModel(user, "name"));
        TextField emailId = new TextField("emailId", new PropertyModel(user, "email"));
        name.setRequired(true);
        emailId.setRequired(true);

        form = new Form("regForm") {

            public void onSubmit() {
                parameters.add("name", user.getName());
                parameters.add("email", user.getEmail());
                parameters.add("createdOn", user.getCreatedAt());
                dao.save(user);
                try {
                    setResponsePage(new Acknowledgement(parameters));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        form.add(name);
        form.add(emailId);
        add(form);
    }
}
