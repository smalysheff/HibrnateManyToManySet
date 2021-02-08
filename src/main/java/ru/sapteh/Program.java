package ru.sapteh;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.Role;
import ru.sapteh.model.User;
import ru.sapteh.service.RoleDaoImp;
import ru.sapteh.service.UserDaoImp;

public class Program {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<Role, Integer> roleDaoImp = new RoleDaoImp(factory);
        Dao<User, Integer> userDaoImp = new UserDaoImp(factory);


        User ivan = userDaoImp.read(1);

        System.out.println(ivan);

        ivan.getRoles().forEach(System.out::println);




        factory.close();

    }
}
