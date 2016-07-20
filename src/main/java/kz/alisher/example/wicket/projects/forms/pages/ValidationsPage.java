package kz.alisher.example.wicket.projects.forms.pages;

import kz.alisher.example.wicket.projects.BasePage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.value.ValueMap;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.*;

import java.io.Serializable;

/**
 * @author Alisher
 */
public class ValidationsPage extends BasePage {
    public ValidationsPage() {
        Form form = new Form("form", new CompoundPropertyModel(
                new ValueMap()));
        add(form);
        form.add(new FeedbackPanel("feedback"));

    /* Section 7.6.1 */
        form.add(new TextField("age1").setRequired(true).setLabel(
                new Model("age")));

    /* Section 7.6.2 */
        form.add(new TextField("age2", new Model(), Integer.class));

    /* Section 7.6.3 */

        form.add(new TextField("age3").add(RangeValidator.minimum(18)));
        form.add(new TextField("handicap").add(RangeValidator.range(0, 3)));
        form.add(new TextField("duration"));

        form.add(new TextField("userid").add(StringValidator
                .lengthBetween(8, 12)));
        form.add(new TextField("comment").add(StringValidator
                .maximumLength(4000)));

        form.add(new TextField("phone").add(new PatternValidator(
                "^[2-9]\\d{2}-\\d{3}-\\d{4}$")));

        form.add(new TextField("email").add(EmailAddressValidator
                .getInstance()));
        form.add(new TextField("url").add(new UrlValidator(
                new String[]{"http"})));

        PasswordTextField field1 = new PasswordTextField("password");
        field1.setResetPassword(false);
        form.add(field1);

        PasswordTextField field2 = new PasswordTextField(
                "controlPassword");
        field2.setModel(field1.getModel());
        field2.setResetPassword(false);
        form.add(field2);

        form.add(new EqualPasswordInputValidator(field1, field2));

    /* Section 7.6.4 */
        class DivisibleValidator implements IValidator {
            private final long n;

            public DivisibleValidator(long n) {
                if (n == 0)
                    throw new IllegalArgumentException("n can�t be 0");
                this.n = n;
            }


            public void validate(IValidatable iValidatable) {
                Number value = (Number) iValidatable.getValue();
                // determine whether the input is divisible by n
                if (value.longValue() % n != 0) {
                    error((Serializable) iValidatable);
                }
            }
        }
        form.add(new TextField("divisible", Integer.class).add(new DivisibleValidator(2)).setLabel(new Model("Even number")));
    }
}
