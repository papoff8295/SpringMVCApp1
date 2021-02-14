package ru.popov.springcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.popov.springcourse.dao.PersonDAO;
import ru.popov.springcourse.dto.PersonDTO;
import ru.popov.springcourse.models.Person;

@Controller
@RequestMapping(value = "/admin/")
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

    @DeleteMapping
    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) int userId,
                              @RequestParam(required = true, defaultValue = "" ) String action,
                              Model model) {
        if (action.equals("delete")){
            personDAO.delete(userId);
        }
        return "redirect:/admin";
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable(name = "id") int id) {
        Person person = personDAO.show(id);
        if (person == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        PersonDTO result = PersonDTO.fromPerson(person);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
