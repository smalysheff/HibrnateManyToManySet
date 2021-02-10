package ru.sapteh.model;

import lombok.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    @NonNull
    private String name;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<UserRole> userRoles;

    @Transient
    private int sizeRole;

    @Transient
    public int sizeRoleMethod(){


//        SessionFactory factory = new Configuration().configure().buildSessionFactory();
//        Session session = factory.openSession();
//
//        Query query = session.createNativeQuery("select count(*) from user_role as ur " +
//                "left join user as u on ur.user_id=u.id " +
//                "left join role as r on ur.role_id=r.id where id=" + getId());

        return 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
