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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Person> getPerson() {
        return person;
    }

    @NotEmpty
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<Person> person;
}
