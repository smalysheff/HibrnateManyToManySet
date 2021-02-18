package ru.sapteh.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.Role;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class RoleDaoImp implements Dao<Role, Integer> {
    private final SessionFactory factory;

    public RoleDaoImp(SessionFactory factory){
        this.factory = factory;
    }


    @Override
    public Role findById(Integer id) {
        try(Session session = factory.openSession()){
            Query<Role> query = session.createNativeQuery("select * from role where id = :id", Role.class)
                    .setParameter("id", id);
            return query.getSingleResult();
        }
    }

    @Override
    public List<Role> findByAll() {
        try(Session session = factory.openSession()){
            Query<Role> result = session.createQuery("FROM Role");
            return result.list();
        }
    }

    @Override
    public void create(Role role) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            Query<Role> query = session.createNativeQuery("insert into role (name) values (:name)", Role.class);
            query.setParameter("name", role.getName());
//            Query<Role> query = session.createNativeQuery("insert into role (name) values (?)", Role.class);
//            query.setParameter(1, role.getName());
            query.executeUpdate();
            session.getTransaction().commit();

        }
    }

    @Override
    public void update(Role role) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            Query<Role> query = session.createNativeQuery("update role set name = :name where id = :id", Role.class);
            query.setParameter("name", role.getName());
            query.setParameter("id", role.getId());
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Role role) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            Query<Role> query = session.createNativeQuery("delete from role where id = :id", Role.class);
            query.setParameter("id", role.getId());
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }
}
