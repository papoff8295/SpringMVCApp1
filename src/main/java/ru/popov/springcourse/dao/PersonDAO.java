package ru.popov.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.popov.springcourse.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Tom", 24, "tom@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Jerry", 32, "jerry@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Pluto", 8, "pluto@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 9, "bob@mail.ru"));
    }

    public List<Person> index() {

    return people;
    }

    public Person show(final int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person) {
        Person personForUpdate = show(id);
        personForUpdate.setName(person.getName());
        personForUpdate.setAge(person.getAge());
        personForUpdate.setEmail(person.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
