package ru.popov.springcourse.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.popov.springcourse.models.Person;

@JsonIgnoreProperties
public class PersonDTO {
    private int id;
    private String name;
    private int age;
    private String email;

    public Person toPerson() {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setAge(age);
        person.setEmail(email);
        return person;
    }

    public static PersonDTO fromPerson(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(person.getId());
        personDTO.setName(person.getName());
        personDTO.setAge(person.getAge());
        personDTO.setEmail(person.getEmail());
        return personDTO;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}
