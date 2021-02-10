package ru.sapteh.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.sapteh.dao.Dao;
import ru.sapteh.dao.RoleDaoImp;
import ru.sapteh.model.Role;

import java.util.List;

import static org.junit.Assert.*;

public class RoleDaoImpTest {
    private SessionFactory factory;
    private Session session;
    private Role role;
    private Dao<Role, Integer> roleDaoImp;
    private List<Role> roleListTest;

    @Before
    public void setUp() throws Exception {
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        roleDaoImp = new RoleDaoImp(factory);
        roleListTest = roleDaoImp.findByAll();
    }

    @After
    public void tearDown() throws Exception {
        session.close();
        factory.close();
        role = null;
        roleDaoImp = null;
    }

    @Test
    public void read() {
        role = roleDaoImp.read(1);
        Assert.assertNotNull(role);
        System.out.println(role);
    }

    @Test
    public void findByAll() {
        List<Role> roles = roleDaoImp.findByAll();
        Assert.assertNotNull(roles);
        roles.forEach(System.out::println);
    }

    @Test
    public void create() {
        role = new Role("Ivan");
        roleDaoImp.create(role);
        assertNotNull(roleDaoImp.read(role.getId()));
    }

    @Test
    public void update() {
        role = roleDaoImp.read(8);
        role.setName("registrator");
        roleDaoImp.update(role);

    }

    @Test
    public void delete() {
        roleDaoImp.delete(roleDaoImp.read(roleListTest.size()-1));
        assertNull(roleDaoImp.read(roleListTest.size()-1));
    }
}