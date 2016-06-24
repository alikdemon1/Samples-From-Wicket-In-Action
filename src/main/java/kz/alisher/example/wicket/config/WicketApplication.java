package kz.alisher.example.wicket.config;

import kz.alisher.example.wicket.model.Project;
import kz.alisher.example.wicket.projects.Start;
import kz.alisher.example.wicket.projects.cheesr.model.Cheese;
import kz.alisher.example.wicket.projects.cheesr.CheesrSession;
import kz.alisher.example.wicket.projects.cheesr.pages.Index;
import kz.alisher.example.wicket.projects.understanding_models.part1.ClockPage;
import kz.alisher.example.wicket.projects.counter.Counter;
import kz.alisher.example.wicket.projects.echo.Echo;
import kz.alisher.example.wicket.projects.registration.RegistrationPage;
import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Alisher on 04.06.2016.
 */
public class WicketApplication extends WebApplication {

    private List<Cheese> cheeses = Arrays.asList(
            new Cheese("Gouda", "Gouda is a yellowish Dutch[...]", 1.65),
            new Cheese("Edam", "Edam (Dutch Edammer) is a D[...]", 1.05),
            new Cheese("Maasdam", "Maasdam cheese is a Dutc[...]", 2.35),
            new Cheese("Brie", "Brie is a soft cows' milk c[...]", 3.15),
            new Cheese("Buxton Blue", "Buxton Blue cheese i[...]", 0.99),
            new Cheese("Parmesan", "Parmesan is a grana, a [...]", 1.99),
            new Cheese("Cheddar", "Cheddar cheese is a hard[...]", 2.95),
            new Cheese("Roquefort", "Roquefort is a ewe's-m[...]", 1.67),
            new Cheese("Boursin", "Boursin Cheese is a soft[...]", 1.33),
            new Cheese("Camembert", "Camembert is a soft, c[...]", 1.69),
            new Cheese("Emmental", "Emmental is a yellow, m[...]", 2.39),
            new Cheese("Reblochon", "Reblochon is a French [...]", 2.99));


    private List<Project> projects = Arrays.asList(
            new Project("Counter App", Counter.class),
            new Project("Echo App", Echo.class),
            new Project("Cheesr Shop App", Index.class),
            new Project("Registration App", RegistrationPage.class),
            new Project("Understanding Models App", ClockPage.class)
    );


    @Override
    public Session newSession(Request request, Response response) {
        return new CheesrSession(request);
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

    @Override
    protected void init() {
        super.init();
        onInitialize();
    }

    private void onInitialize() {
        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    }
}