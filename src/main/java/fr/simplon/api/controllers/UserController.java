package fr.simplon.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.simplon.api.models.User;
import fr.simplon.api.repositories.UserRepository;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    // remplace l'injection par constructeur :
    //    public LoginController(UserRepository userRepository) {
    //        this.userRepository = userRepository;
    //    }

    //récupérer un utilisateur par son id
    @GetMapping("/{userId}")
    public Optional<User> getOneUser(@PathVariable Integer userId) {
        return userRepository.findById(userId);
    }
    // renvoie un optional user, Spring gère le fait qu'il trouve ou non le user vu qu'il est optionnel

    //récupérer tous les utilisateurs
    @GetMapping
    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    //créer un utilisateur
    @PostMapping
    public User createUser(@RequestBody User user) {
       User newUser = new User(user.getUsername());
        return userRepository.save(newUser);
    }

    //modifier un utilisateur
    @PatchMapping("{id}")
    public User updateUser(@PathVariable Integer id, @RequestBody User user) {
        User newUser = userRepository.findById(id).get();
        newUser.setUsername(user.getUsername());
        return userRepository.save(newUser);
    }

    //supprimer un utilisateur
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
}
