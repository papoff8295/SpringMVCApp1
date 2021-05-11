package ru.popov.springcourse.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.popov.springcourse.models.Person;
import ru.popov.springcourse.models.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SpringSecurityPersonFactory {

    public SpringSecurityPersonFactory() {
    }

    public static SpringSecurityPerson create(Person person) {
        return new SpringSecurityPerson(person.getId(), person.getName(), person.getAge(),
                person.getEmail(), person.getPassword(), mapToGrantedAuthorities(new ArrayList<>(person.getRoles())));
    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
