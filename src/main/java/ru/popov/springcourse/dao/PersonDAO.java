package ru.popov.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.popov.springcourse.models.Person;
import ru.popov.springcourse.models.Role;
import ru.popov.springcourse.repository.PersonRepository;
import ru.popov.springcourse.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonDAO {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PersonDAO(PersonRepository personRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Person register(Person person) {
       Role roleUser = roleRepository.findByName("ROLE_USER");
       List<Role> userRoles = new ArrayList<>();
       userRoles.add(roleUser);
       person.setPassword(passwordEncoder.encode(person.getPassword()));
       person.setRoles(userRoles);
        return personRepository.save(person);

    }

    public List<Person> index() {
        return personRepository.findAll();
    }

    public Person findByPersonName(String personName) {

        return personRepository.findByPersonName(personName);
    }

    public Person show(int id) {

        return personRepository.findById(id).orElse(null);
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public void update(int id, Person person) {
        Person updatePerson = show(id);
        updatePerson.setName(person.getName());
        updatePerson.setAge(person.getAge());
        updatePerson.setPassword(person.getPassword());
        updatePerson.setEmail(person.getEmail());
        personRepository.save(updatePerson);
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }


}
