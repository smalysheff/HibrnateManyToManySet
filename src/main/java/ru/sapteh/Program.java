package ru.sapteh;

import lombok.val;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.Dao;
import ru.sapteh.dao.impl.UserDaoImp;
import ru.sapteh.model.User;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<User, Integer> userDaoImpl = new UserDaoImp(factory);

        User user = userDaoImpl.findById(1);



    }
}
