package ru.popov.springcourse.models;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;

    @NotEmpty
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<Person> persons;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Person> getPersons() {
        return persons;
    }


}
