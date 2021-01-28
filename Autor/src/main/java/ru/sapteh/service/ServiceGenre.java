package ru.sapteh.service;

import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.Genre;

import javax.persistence.Query;
import java.util.List;

public class ServiceGenre implements Dao<Genre, Long> {
    private final SessionFactory factory;

    public ServiceGenre(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(@NotNull final Genre genre) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(genre);
            session.getTransaction().commit();
        }
    }

    @Override
    public Genre read(@NotNull final Long id) {
        try(Session session = factory.openSession()) {
            return session.get(Genre.class, id);
        }
    }

    @Override
    public List<Genre> readByAll() {
        try(Session session = factory.openSession()){
            String sql = "SELECT * FROM genre";
            Query query = session.createNativeQuery(sql).addEntity(Genre.class);
            return query.getResultList();
        }
    }

    @Override
    public void update(@NotNull final Genre genre) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(genre);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(@NotNull final Genre genre) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(genre);
            session.getTransaction().commit();
        }
    }
}
