package kz.alisher.example.wicket.model;

import java.io.Serializable;

/**
 * Created by Alisher on 23.06.2016.
 */
public class Project implements Serializable{
    private String name;
    private Class className;

    public Project() {
    }

    public Project(String name, Class className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }
}
