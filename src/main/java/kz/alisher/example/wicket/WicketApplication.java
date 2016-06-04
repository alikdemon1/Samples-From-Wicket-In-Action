package kz.alisher.example.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * Created by Alisher on 04.06.2016.
 */
public class WicketApplication extends WebApplication {
    public Class<? extends Page> getHomePage() {
        return RegistrationPage.class;
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
