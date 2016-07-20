package kz.alisher.example.wicket.projects.cheesr.auth;

import kz.alisher.example.wicket.projects.ApplicationSession;
import org.apache.wicket.authroles.authorization.strategies.role.IRoleCheckingStrategy;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;

/**
 * Created by Alisher on 07.07.2016.
 */
public class UserRolesAuthorizer implements IRoleCheckingStrategy {

    public UserRolesAuthorizer() {
    }


    public boolean hasAnyRole(Roles roles) {
        ApplicationSession session = ApplicationSession.get();
        return session.getUser().isAdmin();
    }
}
