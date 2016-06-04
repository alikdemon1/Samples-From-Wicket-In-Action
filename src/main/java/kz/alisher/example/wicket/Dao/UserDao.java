package kz.alisher.example.wicket.Dao;

import kz.alisher.example.wicket.model.User;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Alisher on 04.06.2016.
 */
public class UserDao {

    @Resource
    SessionFactory sessionFactory;

    @Transactional
    public void save(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

}
