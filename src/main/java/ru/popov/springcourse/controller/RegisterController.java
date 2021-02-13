package ru.popov.springcourse.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.popov.springcourse.dao.PersonDAO;
import ru.popov.springcourse.dto.RegisterPersonDTO;
import ru.popov.springcourse.models.Person;
import ru.popov.springcourse.security.jwt.JwtTokenProvider;


@Controller
@RequestMapping("/register")
@Validated
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
