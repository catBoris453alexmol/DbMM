package ru.sapteh.service;

import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.Books;

import javax.persistence.Query;
import java.util.List;

public class ServiceBooks implements Dao<Books, Long> {
    private final SessionFactory factory;

    public ServiceBooks(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(@NotNull final Books books) {
        try(Session session = factory.openSession()){
            session.beginTransaction();
            session.save(books);
            session.getTransaction().commit();
        }
    }

    @Override
    public Books read(@NotNull final Long id) {
        try(Session session = factory.openSession()) {
            return session.get(Books.class, id);
        }
    }

    @Override
    public List<Books> readByAll() {
        try(Session session = factory.openSession()){
            String sql = "SELECT * FROM autor";
            Query query = session.createNativeQuery(sql).addEntity(Books.class);
            return query.getResultList();
        }
    }

    @Override
    public void update(@NotNull final Books books) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(books);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(@NotNull final Books books) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.delete(books);
            session.getTransaction().commit();
        }
    }
}
