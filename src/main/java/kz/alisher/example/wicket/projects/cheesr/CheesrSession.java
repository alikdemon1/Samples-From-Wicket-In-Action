package kz.alisher.example.wicket.projects.cheesr;

import kz.alisher.example.wicket.projects.cheesr.model.Cart;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

/**
 * Created by Alisher on 23.06.2016.
 */
public class CheesrSession extends WebSession {

    private Cart cart = new Cart();

    public CheesrSession(Request request) {
        super(request);
    }

    public Cart getCart() {
        return cart;
    }
}
