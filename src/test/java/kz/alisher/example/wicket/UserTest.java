package kz.alisher.example.wicket;

import kz.alisher.example.wicket.dao.UserDao;
import kz.alisher.example.wicket.model.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Alisher on 23.06.2016.
 */
public class UserTest {

    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        userDao = (UserDao) ctx.getBean("userDao");
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setName("Test");
        user.setEmail("test@gmail.com");
        user.setCreatedAt(new Date());
        userDao.save(user);
        assertNotNull(user.getId());
    }

    @Test
    public void testFindAll() throws Exception {
        List<User> users = userDao.findAll();
        assertNotNull(users);
    }

    @Test
    public void testFindById() throws Exception {
        User user = new User();
        user.setName("Test");
        user.setEmail("test@gmail.com");
        user.setCreatedAt(new Date());
        userDao.save(user);
        User receivedUser = userDao.findById(user.getId());
        assertEquals("Test", receivedUser.getName());
    }

    @Test
    public void testDeleteUser() throws Exception {
        User user = new User();
        user.setName("Test");
        user.setEmail("test@gmail.com");
        user.setCreatedAt(new Date());
        userDao.save(user);
        userDao.delete(user);
        assertNotNull(user);
    }
}