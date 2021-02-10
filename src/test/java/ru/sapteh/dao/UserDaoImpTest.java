package ru.sapteh.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.sapteh.model.Role;
import ru.sapteh.model.User;

import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImpTest {
    private SessionFactory factory;
    private Session session;
    private User user;
    private UserDaoImp userDaoImp;

    @Before
    public void setUp() throws Exception {
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        userDaoImp = new UserDaoImp(factory);
    }

    @After
    public void tearDown() throws Exception {
        session.close();
        factory.close();
        user = null;
        userDaoImp = null;
    }

    @Test
    public void read() {
    }

    @Test
    public void findByAll() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void sqlNativeTest(){
        List<User> users = userDaoImp.sqlNative();

        assertNull(users);
//        users.forEach(System.out::println);

    }

}