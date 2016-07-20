package kz.alisher.example.wicket.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Alisher on 04.06.2016.
 */
@Entity
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "User.findById",
                query = "select distinct u from User u where u.id = :id")})
public class User implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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