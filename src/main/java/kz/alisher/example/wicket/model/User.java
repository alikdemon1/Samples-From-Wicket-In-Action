package kz.alisher.example.wicket.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Alisher on 04.06.2016.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public User() {
    }

    public Date getCreatedAt() {
        createdAt = new Date();
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createdOn=" + createdAt +
                '}';
    }
}
