package ru.popov.springcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.popov.springcourse.dao.PersonDAO;
import ru.popov.springcourse.dto.AuthRequestDto;
import ru.popov.springcourse.models.Person;
import ru.popov.springcourse.security.jwt.JwtTokenProvider;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final PersonDAO personDAO;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public LoginController(PersonDAO personDAO, AuthenticationManager authenticationManager1, JwtTokenProvider jwtTokenProvider1) {
        this.personDAO = personDAO;
        this.authenticationManager = authenticationManager1;
        this.jwtTokenProvider = jwtTokenProvider1;
    }

    @PostMapping()
    public ResponseEntity login(@RequestBody AuthRequestDto authRequestDto) {
        try {
            String personName = authRequestDto.getPersonName();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(personName, authRequestDto.getPassword()));
            Person person = personDAO.findByPersonName(personName);
            if (person == null) {
                throw new UsernameNotFoundException("User not found");
            }
            String token = jwtTokenProvider.createToken(personName, person.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("personName", personName);
            response.put("token", token);
            //response.put("id", person.getId());
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid data");
        }
    }
}
