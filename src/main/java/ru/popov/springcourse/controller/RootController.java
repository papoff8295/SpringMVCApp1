package ru.popov.springcourse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:index";
    }

    //    @Secured("ROLE_ADMIN")
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/users")
//    public String getUsers() {
//        return "users";
//    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("/meals")
//    public String getMeals() {
//        return "meals";
//    }
}
