package kz.alisher.example.wicket.projects.cheesr.pages;

import kz.alisher.example.wicket.projects.BasePage;
import kz.alisher.example.wicket.projects.cheesr.auth.SignOutPage;
import kz.alisher.example.wicket.projects.cheesr.auth.UserPanel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Created by Alisher on 23.06.2016.
 */
@AuthorizeInstantiation("ADMIN")
public class Index extends BasePage {
    private ShoppingCartPanel shoppingCart = new ShoppingCartPanel("cart", getCart(), true);
    private Panel cheesesPanel = new CheesesPanel("main", getCheeses(), getCart(), shoppingCart);

    private Panel recipesPanel = new RecipesPanel("main");

    private Panel current = cheesesPanel;

    private WebMarkupContainer menu = new WebMarkupContainer("menu");

    public Index() {
        cheesesPanel.setOutputMarkupId(true);
        recipesPanel.setOutputMarkupId(true);
        menu.setOutputMarkupId(true);
        add(menu);
        menu.add(new AjaxLink("cheeseslink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                current.replaceWith(cheesesPanel);
                current = cheesesPanel;
                target.add(current);
                target.add(menu);
            }

            @Override
            public boolean isEnabled() {
                return current != cheesesPanel;
            }
        });
        menu.add(new AjaxLink("recipeslink") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                current.replaceWith(recipesPanel);
                current = recipesPanel;
                target.add(current);
                target.add(menu);
            }

            @Override
            public boolean isEnabled() {
                return current != recipesPanel;
            }
        });

        add(current);
        add(shoppingCart);
        add(new UserPanel("userPanel", SignOutPage.class));
    }
}
