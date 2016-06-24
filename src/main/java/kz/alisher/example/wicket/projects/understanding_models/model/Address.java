package kz.alisher.example.wicket.projects.understanding_models.model;

import java.io.Serializable;

/**
 * Created by Alisher on 24.06.2016.
 */
public class Address implements Serializable {
    private String street;

    /**
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }
}
