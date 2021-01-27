package ru.popov.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.popov.springcourse.models.Person;
import ru.popov.springcourse.models.Role;

import java.util.List;

@Repository
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate, BCryptPasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }
    public Person register(Person person) {
        List<Role> userRole = jdbcTemplate.query("SELECT * FROM Roles WHERE name=?", new Object[]{"ROLE_USER"}, new BeanPropertyRowMapper<>(Role.class));
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRoles(userRole);
        return person;

    }

    public List<Person> index() {

        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person findByPersonName(String personName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE name=?", new Object[]{personName}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public Person show(final int id) {

        return jdbcTemplate.query("SELECT * FROM Person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person (name, age, email) VALUES(?, ?, ?)", person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE Person SET name=?, age=?, email=? WHERE id=?",
                person.getName(), person.getAge(), person.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
