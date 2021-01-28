package ru.sapteh.service;

import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.Autors;

import javax.persistence.Query;
import java.util.List;

public class ServiceAutors implements Dao<Autors, Long> {
    private final SessionFactory factory;

    public ServiceAutors(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(@NotNull final Autors autor) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(autor);
            session.getTransaction().commit();
        }
    }

    @Override
    public Autors read(@NotNull final Long id) {
        try(Session session = factory.openSession()) {
            return session.get(Autors.class, id);
        }
    }

    @Override
    public List<Autors> readByAll() {
        try(Session session = factory.openSession()){
            String sql = "SELECT * FROM autor";
            Query query = session.createNativeQuery(sql).addEntity(Autors.class);
            return query.getResultList();
        }
    }

    @Override
    public void update(@NotNull final Autors autor) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(autor);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(@NotNull final Autors autor) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(autor);
            session.getTransaction().commit();
        }
    }
}
