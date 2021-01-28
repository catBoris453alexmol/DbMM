package ru.sapteh;

import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.Dao;
import ru.sapteh.model.Autors;
import ru.sapteh.model.Books;
import ru.sapteh.model.Genre;
import ru.sapteh.model.Users;
import ru.sapteh.service.ServiceAutors;
import ru.sapteh.service.ServiceBooks;
import ru.sapteh.service.ServiceGenre;
import ru.sapteh.service.ServiceUsers;

import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Dao<Autors, Long> serviceAutor = new ServiceAutors(factory);
        Dao<Genre, Long> serviceGenre = new ServiceGenre(factory);
        Dao<Users, Long> serviceUsers = new ServiceUsers(factory);
        Dao<Books, Long> serviceBooks = new ServiceBooks(factory);

        Genre genre = new Genre();
        genre.setGenre_name("Manga");
        Autors autor = new Autors();
        Users users = new Users();
        Books books = new Books();
        autor.setFirstName("Цугуми");
        autor.setLastName("Оба");
        autor.setAge(45);
        Set<Autors> autors = new HashSet<>();
        autors.add(autor);
        Set<Books> booksSet = new HashSet<>();
        booksSet.add(books);
        books.setPublishing_year(2020);
        books.setTitle("dfdfd");
        books.setGenre(genre);

        Set<Users> usersSet = new HashSet<>();
        usersSet.add(users);
        users.setUsername("fdfdfd");
        users.setAge(28);

        //Genre genre = serviceGenre.read((long)2);
        autor.setBooks(booksSet);
        users.setBooks(booksSet);
        books.setUsersSet(usersSet);

        //serviceAutor.create((Autors) autor);
        //serviceGenre.create((Genre) genre);
        //serviceUsers.create((Users) users);
        serviceBooks.create((Books) books);

        factory.close();
    }
}
