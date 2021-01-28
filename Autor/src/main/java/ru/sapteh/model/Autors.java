package ru.sapteh.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "autors")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Autors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "age", nullable = false)
    private int age;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "autors_books",
            joinColumns=@JoinColumn(name = "autors_id"),
            inverseJoinColumns = @JoinColumn(name = "books_id")
    )


    private Set<Books> books;


    @Override
    public String toString() {
        return "Autors{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
