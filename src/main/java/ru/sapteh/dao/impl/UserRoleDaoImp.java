package ru.sapteh.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.Role;
import ru.sapteh.model.User;
import ru.sapteh.model.UserRole;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class UserRoleDaoImp implements Dao<UserRole, Integer> {
    private final SessionFactory factory;

    public UserRoleDaoImp(SessionFactory factory) {
        this.factory = factory;
    }


    @Override
    public UserRole findById(Integer id) {
        try (Session session = factory.openSession()) {
            UserRole result = session.get(UserRole.class, id);

            if (result != null){
                Hibernate.initialize(result.getRole());
                Hibernate.initialize(result.getUser());
            }
            return result;
        }
    }

    @Override
    public List<UserRole> findByAll() {
        try (Session session = factory.openSession()) {
            Query<UserRole> result = session.createQuery("FROM UserRole");
            return result.list();
        }
    }

    @Override
    public void create(UserRole userRole) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(userRole);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(UserRole userRole) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(userRole);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(UserRole userRole) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(userRole);
            session.getTransaction().commit();
        }
    }
}
