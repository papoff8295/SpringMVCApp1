package ru.popov.springcourse.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.popov.springcourse.models.Person;
import ru.popov.springcourse.models.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtPersonFactory {
    public JwtPersonFactory() {}

    public static JwtPerson create(Person person) {
        return new JwtPerson(person.getId(),
                person.getName(),
                person.getAge(),
                person.getEmail(),
                person.getPassword(),
                mapToGrantedAuthorities(new ArrayList<>(person.getRoles())));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> personRole){
        return personRole.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
