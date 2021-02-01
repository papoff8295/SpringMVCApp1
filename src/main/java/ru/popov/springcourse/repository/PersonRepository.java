package ru.popov.springcourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.popov.springcourse.models.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Person findByName(String name);
}
