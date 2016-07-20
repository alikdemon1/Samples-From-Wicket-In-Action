package kz.alisher.example.wicket.projects.repeat;

import java.io.Serializable;

/**
 * Created by Alisher on 27.06.2016.
 */
public class MenuItem implements Serializable{
    private String caption;
    private Class destination;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Class getDestination() {
        return destination;
    }

    public void setDestination(Class destination) {
        this.destination = destination;
    }
}
