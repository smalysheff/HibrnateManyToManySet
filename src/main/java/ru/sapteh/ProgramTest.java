package ru.sapteh;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.Role;
import ru.sapteh.model.User;
import ru.sapteh.model.UserRole;
import ru.sapteh.service.RoleDaoImp;
import ru.sapteh.service.UserDaoImp;
import ru.sapteh.service.UserRoleDaoImp;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class ProgramTest {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        Dao<Role, Integer> roleDaoImp = new RoleDaoImp(factory);
        Dao<User, Integer> userDaoImp = new UserDaoImp(factory);
        Dao<UserRole, Integer> userRoleDaoImp = new UserRoleDaoImp(factory);


//        User user = userDaoImp.read(2);
//
//        userDaoImp.delete(user);


//        userRoleDaoImp.create(new UserRole(Calendar.getInstance().getTime(), roleDaoImp.read(3), userDaoImp.read(5)));


//        Role role = roleDaoImp.read(1);
//
//        Set<UserRole> userRoles = role.getUserRoles();
//
//        userRoles.forEach(System.out::println);

//        System.out.println(userRoles);

//
//       userDaoImp.findByAll().forEach(user -> {
//           System.out.println(user.sizeRoleMethod());
//       });
////
//
//
//        System.out.println(userRoles.size());



        factory.close();

    }
}
