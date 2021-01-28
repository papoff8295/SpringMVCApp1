package ru.popov.springcourse.Controller;


import org.springframework.beans.factory.annotation.Autowired;
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
import ru.popov.springcourse.models.Person;
import ru.popov.springcourse.security.jwt.JwtTokenProvider;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public PeopleController(PersonDAO personDAO, AuthenticationManager authenticationManager1, JwtTokenProvider jwtTokenProvider1) {
        this.personDAO = personDAO;
        this.authenticationManager = authenticationManager1;
        this.jwtTokenProvider = jwtTokenProvider1;
    }
    @PostMapping("/login")
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
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid data");
        }
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "/WEB-INF/views/people/new.html";
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) return "/WEB-INF/views/people/edit.html";
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }

}
