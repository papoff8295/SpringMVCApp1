package ru.popov.springcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.popov.springcourse.dao.PersonDAO;
import ru.popov.springcourse.dto.RegisterPersonDTO;
import ru.popov.springcourse.models.Person;

import javax.validation.Valid;


@Controller
@RequestMapping("/register")
public class RegisterController {
    private final PersonDAO personDAO;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public RegisterController(PersonDAO personDAO, AuthenticationManager authenticationManager1) {
        this.personDAO = personDAO;
        this.authenticationManager = authenticationManager1;
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
        Person person = registerPersonDTO.toPerson();
        //PersonDTO result = PersonDTO.fromPerson(registerPerson);
        if (!personDAO.register(person)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        model.addAttribute("userRegister", "Регистрация прошла успешно");
        return "index";
    }

}
