package fr.simplon.api.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.simplon.api.models.User;
import fr.simplon.api.repositories.UserRepository;
import fr.simplon.api.exceptions.InvalidCredentialsException;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public User login(
            @ModelAttribute("username") String username,
            @ModelAttribute("password") String password
    ) throws InvalidCredentialsException {
        //return userRepository.findByUsernameAndPassword(username, password);
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(() -> new InvalidCredentialsException("Check your credentials"));
    }


//    @PostMapping("/register")
//    public User createUser(User user) {
//        return userRepository.save(user);
//    }

    @PostMapping("/register")
    public User register(
            @ModelAttribute("username") String username,
            @ModelAttribute("password") String password
    ) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            return userRepository.save(user);
    }
}
