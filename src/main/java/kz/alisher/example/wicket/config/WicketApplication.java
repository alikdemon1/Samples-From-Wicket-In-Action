package kz.alisher.example.wicket.config;

import kz.alisher.example.wicket.model.Project;
import kz.alisher.example.wicket.projects.ApplicationSession;
import kz.alisher.example.wicket.projects.Start;
import kz.alisher.example.wicket.projects.cheesr.auth.AuthenticatedWebPage;
import kz.alisher.example.wicket.projects.cheesr.auth.SigninPage;
import kz.alisher.example.wicket.projects.cheesr.auth.UserRolesAuthorizer;
import kz.alisher.example.wicket.projects.cheesr.model.Cheese;
import kz.alisher.example.wicket.projects.cheesr.model.User;
import kz.alisher.example.wicket.projects.cheesr.pages.Checkout;
import kz.alisher.example.wicket.projects.cheesr.pages.Index;
import kz.alisher.example.wicket.projects.common_task.CommonTask;
import kz.alisher.example.wicket.projects.counter.Counter;
import kz.alisher.example.wicket.projects.echo.Echo;
import kz.alisher.example.wicket.projects.forms.FormPage;
import kz.alisher.example.wicket.projects.registration.RegistrationPage;
import kz.alisher.example.wicket.projects.repeat.RepeatComponents;
import kz.alisher.example.wicket.projects.understanding_models.part1.ClockPage;
import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.RoleAuthorizationStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Alisher on 04.06.2016.
 */
public class WicketApplication extends WebApplication {

    private Map<String, User> users = new HashMap<String, User>();

    private List<Cheese> cheeses = new ArrayList<Cheese>();

    private List<Project> projects = Arrays.asList(
            new Project("Counter App", Counter.class),
            new Project("Echo App", Echo.class),
            new Project("Cheesr Shop App", SigninPage.class),
            new Project("Registration App", RegistrationPage.class),
            new Project("Understanding Models App", ClockPage.class),
            new Project("Repeating Components App", RepeatComponents.class),
            new Project("Common task with Components App", CommonTask.class),
            new Project("Forms examples App", FormPage.class)
    );

    @Override
    public Session newSession(Request request, Response response) {
        return new ApplicationSession(request);
    }

    public static WicketApplication get() {
        return (WicketApplication) Application.get();
    }

    public List<Cheese> getCheeses() {
        return Collections.unmodifiableList(cheeses);
    }

    public List<Project> getProjects() {
        return Collections.unmodifiableList(projects);
    }

    public Class<? extends Page> getHomePage() {
        return Start.class;
    }

    public Map<String, User> getUsers() {
        return users;
    }


    @Override
    protected void init() {
        super.init();

        readCheeses();
        onInitialize();

        users.put("user", new User("user", "user", "USER", false));
        users.put("admin", new User("admin", "admin", "ADMIN", true));

        getSecuritySettings().setAuthorizationStrategy(
                new RoleAuthorizationStrategy(new UserRolesAuthorizer()));
        MetaDataRoleAuthorizationStrategy.authorize(Index.class, "ADMIN");
        MetaDataRoleAuthorizationStrategy.authorize(Checkout.class, "ADMIN");


        getSecuritySettings().setAuthorizationStrategy(new IAuthorizationStrategy.AllowAllAuthorizationStrategy() {
            @Override
            public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
                    Class<T> componentClass) {
                // Check if the new Page requires authentication (implements the marker interface)
                if (AuthenticatedWebPage.class.isAssignableFrom(componentClass)) {
                    // Is user signed in?
                    if (((ApplicationSession) Session.get()).isSignedIn()) {
                        // okay to proceed
                        return true;
                    }

                    // Intercept the request, but remember the target for later.
                    // Invoke Component.continueToOriginalDestination() after successful logon to
                    // continue with the target remembered.

                    throw new RestartResponseAtInterceptPageException(SigninPage.class);
                }

                // okay to proceed
                return true;
            }
        });
    }


    private void onInitialize() {
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    }

    private void readCheeses() {
        Properties props = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = WebApplication.class.getClassLoader().getResourceAsStream("cheeses.properties");
            props.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Object obj : props.keySet()) {
            String key = obj.toString();

            // only process a cheese once (identified by its name)
            if (!key.endsWith(".name"))
                continue;
            key = key.substring(0, key.indexOf("."));

            // retrieve each property value
            String name = props.getProperty(key + ".name");
            String description = props.getProperty(key + ".description");
            double price = Double.valueOf(props.getProperty(key + ".price"));

            cheeses.add(new Cheese(name, description, price));
        }
    }
}