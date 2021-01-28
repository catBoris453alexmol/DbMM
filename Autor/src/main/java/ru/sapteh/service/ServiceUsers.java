package ru.sapteh.service;

import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.Genre;
import ru.sapteh.model.Users;

import javax.persistence.Query;
import java.util.List;

public class ServiceUsers implements Dao<Users,Long> {
    private final SessionFactory factory;

    public ServiceUsers(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(@NotNull final Users users) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(users);
            session.getTransaction().commit();
        }
    }

    @Override
    public Users read(@NotNull final Long id) {
        try(Session session = factory.openSession()) {
            return session.get(Users.class, id);
        }
    }

    @Override
    public List<Users> readByAll() {
        try(Session session = factory.openSession()){
            String sql = "SELECT * FROM users";
            Query query = session.createNativeQuery(sql).addEntity(Users.class);
            return query.getResultList();
        }
    }

    @Override
    public void update(@NotNull final Users users) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(users);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(@NotNull final Users users) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(users);
            session.getTransaction().commit();
        }
    }
}
