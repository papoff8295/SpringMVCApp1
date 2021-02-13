package ru.popov.springcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.popov.springcourse.dao.PersonDAO;


@Controller
public class RootController {

    private final PersonDAO personDAO;

    @Autowired
    public RootController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

//    @GetMapping("/login")
//    public String login() {
//
//        return "login";
//    }

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
