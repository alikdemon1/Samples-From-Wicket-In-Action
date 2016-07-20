package kz.alisher.example.wicket.projects;

import kz.alisher.example.wicket.config.WicketApplication;
import kz.alisher.example.wicket.model.Project;
import kz.alisher.example.wicket.projects.cheesr.model.Cart;
import kz.alisher.example.wicket.projects.cheesr.model.Cheese;
import kz.alisher.example.wicket.projects.cheesr.model.User;
import org.apache.wicket.markup.html.WebPage;

import java.util.List;
import java.util.Map;

/**
 * Created by Alisher on 23.06.2016.
 */
public class BasePage extends WebPage {

    public ApplicationSession getCheesrSession() {
        return (ApplicationSession) getSession();
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

    public Map<String,User> getUsers() {
        return WicketApplication.get().getUsers();
    }
}