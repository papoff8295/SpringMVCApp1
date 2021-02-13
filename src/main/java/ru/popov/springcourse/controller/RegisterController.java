package ru.popov.springcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.popov.springcourse.dao.PersonDAO;
import ru.popov.springcourse.dto.AuthRequestDto;
import ru.popov.springcourse.dto.PersonDTO;
import ru.popov.springcourse.dto.RegisterPersonDTO;
import ru.popov.springcourse.models.Person;
import ru.popov.springcourse.security.jwt.JwtTokenProvider;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final PersonDAO personDAO;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public RegisterController(PersonDAO personDAO, AuthenticationManager authenticationManager1, JwtTokenProvider jwtTokenProvider1) {
        this.personDAO = personDAO;
        this.authenticationManager = authenticationManager1;
        this.jwtTokenProvider = jwtTokenProvider1;
    }

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("userForm", new RegisterPersonDTO());

        return "registration";
    }

    @PostMapping
    public String register(@ModelAttribute("userForm") @Valid RegisterPersonDTO registerPersonDTO,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) return "registration";
        Person person = new Person();
        person.setName(registerPersonDTO.getName());
        person.setPassword(registerPersonDTO.getPassword());
        person.setEmail(registerPersonDTO.getEmail());
        person.setAge(registerPersonDTO.getAge());
        //PersonDTO result = PersonDTO.fromPerson(registerPerson);
        if (!personDAO.register(person)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }


        return "redirect:/";
    }

}
