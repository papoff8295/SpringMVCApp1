package ru.popov.springcourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.popov.springcourse.dao.PersonDAO;
import ru.popov.springcourse.models.Person;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final PersonDAO personDAO;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(PersonDAO personDAO, AuthenticationManager authenticationManager1) {
        this.personDAO = personDAO;
        this.authenticationManager = authenticationManager1;
    }

    @GetMapping
    public String login() {
        return "login";
    }


//    @PostMapping()
//    public ResponseEntity login(@RequestBody AuthRequestDto authRequestDto) {
//        try {
//            String personName = authRequestDto.getPersonName();
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(personName, authRequestDto.getPassword()));
//            Person person = personDAO.findByPersonName(personName);
//            if (person == null) {
//                throw new UsernameNotFoundException("User not found");
//            }
//            String token = jwtTokenProvider.createToken(personName, person.getRoles());
//            Map<Object, Object> response = new HashMap<>();
//            response.put("personName", personName);
//            response.put("token", token);
//            //response.put("id", person.getId());
//            return ResponseEntity.ok(response);
//        } catch (AuthenticationException e) {
//            throw new BadCredentialsException("Invalid data");
//        }
//    }

//    @GetMapping
//    public String login(Model model) {
//        model.addAttribute("userForm", new AuthRequestDto());
//        return "login";
//    }

    @PostMapping
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            UserDetails person = personDAO.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username, "", person.getAuthorities());
            authenticationManager.authenticate(user);

            return "redirect:/";
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid data");
        }
    }
}
