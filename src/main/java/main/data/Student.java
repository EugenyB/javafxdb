package main.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "student")
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "select s from Student s")
})
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;

    private Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}