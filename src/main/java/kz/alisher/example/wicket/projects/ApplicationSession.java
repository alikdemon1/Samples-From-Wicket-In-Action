package kz.alisher.example.wicket.projects;

import kz.alisher.example.wicket.projects.cheesr.model.Cart;
import kz.alisher.example.wicket.projects.cheesr.model.User;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

/**
 * Created by Alisher on 23.06.2016.
 */
public class ApplicationSession extends AuthenticatedWebSession {

    private Cart cart = new Cart();
    private User user;

    public ApplicationSession(Request request) {
        super(request);
    }

    public Roles getRoles() {
        if (isSignedIn()) {
            return new Roles(Roles.ADMIN);
        }
        return null;
    }

    protected boolean authenticate(String username, String password) {
        if (username != null && password != null) {
            User user = new BasePage().getUsers().get(username);
            if (user != null) {
                if (user.getWiaPassword().equals(password)) {
                    ApplicationSession.get().setUser(user);
                    return true;
                }
            }
        }
        return false;
    }

    public static ApplicationSession get() {
        return (ApplicationSession) Session.get();
    }

    public Cart getCart() {
        return cart;
    }

    public boolean isAuthenticated() {
        return (user != null);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}