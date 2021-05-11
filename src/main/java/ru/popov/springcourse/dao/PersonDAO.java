package ru.popov.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.popov.springcourse.dto.SpringSecurityPerson;
import ru.popov.springcourse.dto.SpringSecurityPersonFactory;
import ru.popov.springcourse.models.Person;
import ru.popov.springcourse.models.Role;
import ru.popov.springcourse.repository.PersonRepository;
import ru.popov.springcourse.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonDAO implements UserDetailsService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PersonDAO(PersonRepository personRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public boolean register(Person person) {
        Person checkPerson = personRepository.findByName(person.getName());
        if (checkPerson != null) return false;
       Role roleUser = roleRepository.findByName("ROLE_USER");
       List<Role> userRoles = new ArrayList<>();
       userRoles.add(roleUser);
       person.setPassword(passwordEncoder.encode(person.getPassword()));
       person.setRoles(userRoles);
       personRepository.save(person);
        return true;

    }

    public List<Person> index() {
        return personRepository.findAll();
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


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = personRepository.findByName(s);
        if (person == null) throw new UsernameNotFoundException("User with name:" + s + " not found");
        return SpringSecurityPersonFactory.create(person);
    }
}
