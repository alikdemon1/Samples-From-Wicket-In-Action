package kz.alisher.example.wicket.dao;

import kz.alisher.example.wicket.model.User;

import java.util.List;

/**
 * Created by Alisher on 04.06.2016.
 */
public interface UserDao {
    List<User> findAll();

    void save(User user);

    void delete(User user);

    User findById(Long id);
}