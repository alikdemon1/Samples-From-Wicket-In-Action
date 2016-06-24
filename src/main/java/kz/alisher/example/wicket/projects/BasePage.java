package kz.alisher.example.wicket.projects;

import kz.alisher.example.wicket.config.WicketApplication;
import kz.alisher.example.wicket.model.Project;
import kz.alisher.example.wicket.projects.cheesr.CheesrSession;
import kz.alisher.example.wicket.projects.cheesr.model.Cart;
import kz.alisher.example.wicket.projects.cheesr.model.Cheese;
import org.apache.wicket.markup.html.WebPage;

import java.util.List;

/**
 * Created by Alisher on 23.06.2016.
 */
public class BasePage extends WebPage {

    public CheesrSession getCheesrSession() {
        return (CheesrSession) getSession();
    }

    public Cart getCart() {
        return getCheesrSession().getCart();
    }

    public List<Cheese> getCheeses() {
        return WicketApplication.get().getCheeses();
    }

    public List<Project> getProjects() {
        return WicketApplication.get().getProjects();
    }
}