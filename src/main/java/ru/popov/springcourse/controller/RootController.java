package ru.popov.springcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import ru.popov.springcourse.dao.PersonDAO;
import ru.popov.springcourse.dto.RegisterPersonDTO;
import ru.popov.springcourse.models.Person;

import javax.validation.Valid;

@Controller
public class RootController {

    private final PersonDAO personDAO;

    @Autowired
    public RootController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

//    @GetMapping("/index")
//    public String home()
//    {
//        return "index";
//    }

//    @GetMapping("/register")
//    public String register(ModelMap model) {
//        model.addAttribute("personDTO", new RegisterPersonDTO());
//        model.addAttribute("register", true);
//        return "profile";
//    }
//
//    @PostMapping("/register")
//    public String saveRegister(@Valid RegisterPersonDTO personDTO, BindingResult result, SessionStatus status, ModelMap model) {
//        if (result.hasErrors()) {
//            model.addAttribute("register", true);
//            return "profile";
//        }
//        Person person = personDTO.toPerson();
//        personDAO.register(person);
//        status.setComplete();
//        return "redirect:index";
//    }
}
