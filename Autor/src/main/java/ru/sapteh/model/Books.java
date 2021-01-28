package ru.sapteh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "publishing_year", nullable = false)
    private int publishing_year;



    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Genre genre;

    @ManyToMany(mappedBy = "books")
    private Set<Autors> autors;

    @ManyToMany(mappedBy = "books")
    private Set<Users> usersSet;


    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishing_year='" + publishing_year + '\'' +
                ", genre=" + genre +
                '}';
    }
}
