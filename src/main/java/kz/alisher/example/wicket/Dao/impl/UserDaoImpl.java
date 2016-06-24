package kz.alisher.example.wicket.dao.impl;

import kz.alisher.example.wicket.dao.UserDao;
import kz.alisher.example.wicket.model.User;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Alisher on 23.06.2016.
 */
public class UserDaoImpl implements UserDao {

    @Resource
    SessionFactory sessionFactory;

    @Transactional
    public List<User> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from User u").list();
    }

    @Transactional
    public void save(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Transactional
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Transactional
    public User findById(Long id) {
        return (User) sessionFactory.getCurrentSession().getNamedQuery("User.findById").setParameter("id", id).uniqueResult();
    }
}