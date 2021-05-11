package ru.popov.springcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.popov.springcourse.dao.PersonDAO;
import ru.popov.springcourse.models.Person;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final PersonDAO personDAO;

    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("allUsers", personDAO.index());
        return "admin";
    }

    @PostMapping
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) int userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            personDAO.delete(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable(name = "id") int id, Model model) {
        Person person = personDAO.show(id);
        if (person == null) {
            model.addAttribute("error", "Нет такого");
            return "admin";
        }
        model.addAttribute("person", person);
        return "show";

    }
}
