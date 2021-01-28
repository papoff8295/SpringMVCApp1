package ru.popov.springcourse.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.popov.springcourse.dao.PersonDAO;
import ru.popov.springcourse.models.Person;
import ru.popov.springcourse.security.jwt.JwtPerson;
import ru.popov.springcourse.security.jwt.JwtPersonFactory;

@Service
public class JwtPersonDetailsService implements UserDetailsService {
    private  PersonDAO personDAO;

    @Autowired
    public void setPersonDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = personDAO.findByPersonName(s);
        if (person == null) {
            throw  new UsernameNotFoundException("User not found");
        }
        JwtPerson jwtPerson = JwtPersonFactory.create(person);
        return jwtPerson;
    }
}
