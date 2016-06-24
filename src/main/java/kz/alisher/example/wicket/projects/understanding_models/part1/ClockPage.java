package kz.alisher.example.wicket.projects.understanding_models.part1;

import kz.alisher.example.wicket.projects.BasePage;
import kz.alisher.example.wicket.projects.understanding_models.model.Customer;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Alisher on 24.06.2016.
 */
public class ClockPage extends BasePage {

    private Customer customer = new Customer();

    public ClockPage() {

        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.getAddress().setStreet("Some street");

        add(new Label("firstname0", customer.getFirstName()));
        add(new Label("lastname0", customer.getLastName()));
        add(new Label("street0", customer.getAddress().getStreet()));

        add(new Label("firstname1", new Model(customer.getFirstName())));
        add(new Label("lastname1", new Model(customer.getLastName())));
        add(new Label("street1", new Model(customer.getAddress().getStreet())));

        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
        String time = df.format(new Date());
        add(new Label("static", new Model(time)));

        Model dynamicClock = new Model() {
            @Override
            public Serializable getObject() {
                SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
                String time = df.format(new Date());
                return time;
            }
        };
        add(new Label("dynamic", dynamicClock));

        add(new Link("refresh") {
            public void onClick() {
            }
        });

        class MyForm extends Form {
            private TextField name;

            private TextField street;

            private MyForm(String id) {
                super(id);
                add(name = new TextField("name", new Model("")));
                add(street = new TextField("street", new Model("")));
            }

            protected void onSubmit() {
                Customer customer = new Customer();
                customer.setFirstName((String) name.getModelObject());
                customer.getAddress().setStreet(street.getDefaultModelObjectAsString());
                System.out.println(customer.getAddress().getStreet() + "," + customer.getFirstName());
            }
        }
        add(new MyForm("myform"));

    /*
     * Section 4.2.2
     */

        add(new Label("firstname2", new PropertyModel(customer, "firstName")));
        add(new Label("lastname2", new PropertyModel(customer, "lastName")));
        add(new Label("street2", new PropertyModel(customer, "address.street")));

        add(new Link("person1") {
            public void onClick() {
                customer.setFirstName("John");
                customer.setLastName("Doe");
                customer.getAddress().setStreet("Some street");
            }
        });
        add(new Link("person2") {
            public void onClick() {
                customer.setFirstName("Mister");
                customer.setLastName("Smith");
                customer.getAddress().setStreet("Brinkpoortstraat 11");
            }
        });

        class MyForm2 extends Form {
            public MyForm2(String id) {
                super(id);
                Customer customer = new Customer();
                setModel(new Model(customer));
                add(new TextField("name", new PropertyModel(customer, "firstName")));
                add(new TextField("street", new PropertyModel(customer, "address.street")));
            }

            protected void onSubmit() {
                Customer customer = (Customer) getModelObject();
                String street = customer.getAddress().getStreet();
                // do something with the value of the street property
            }
        }
        add(new MyForm2("myform2"));

    /*
     * Section 4.2.3
     *
     * Added the fields from the book example to a web markup
     * container, to ensure the proper isolation of the example.
     */
        WebMarkupContainer wmc1 = new WebMarkupContainer("cpm1");
        wmc1.setDefaultModel(new CompoundPropertyModel(customer));
        wmc1.add(new Label("firstName"));
        wmc1.add(new Label("lastName"));
        wmc1.add(new Label("address.street"));
        wmc1.add(new Link("person1") {
            public void onClick() {
                customer.setFirstName("John");
                customer.setLastName("Doe");
                customer.getAddress().setStreet("Some street");
            }
        });
        wmc1.add(new Link("person2") {
            public void onClick() {
                customer.setFirstName("Mister");
                customer.setLastName("Smith");
                customer.getAddress().setStreet("Brinkpoortstraat 11");
            }
        });

        add(wmc1);

        class MyForm3 extends Form {
            public MyForm3(String id) {
                super(id, new CompoundPropertyModel(new Customer()));
                add(new TextField("firstName"));
                add(new TextField("address.street"));
            }

            protected void onSubmit() {
                Customer customer = (Customer) getModelObject();
                String street = customer.getAddress().getStreet();
                // do something with the value of the street property
            }
        }
        add(new MyForm3("myform3"));

        class MyForm4 extends Form {
            public MyForm4(String id) {
                super(id);
                CompoundPropertyModel model = new CompoundPropertyModel(new Customer());
                setModel(model);
                add(new TextField("firstName"));
                add(new TextField("street", model.bind("address.street")));
            }

            protected void onSubmit() {
                Customer customer = (Customer) getModelObject();
                String street = customer.getAddress().getStreet();
                // do something with the value of the street property
            }
        }
        add(new MyForm4("myform4"));

    }
}
