package ru.popov.springcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping(value = "people/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable(name = "id") int id) {
        Person person = personDAO.show(id);
        if (person == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        PersonDTO result = PersonDTO.fromPerson(person);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
