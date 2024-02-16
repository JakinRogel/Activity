package com.activity.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.activity.data.UserRepository;
import com.activity.model.User;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String showHomePage(Model model) {
        return "home"; // Thymeleaf template name for the home page
    }
    
    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "userList"; // Thymeleaf template name
    }
    
    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "userDetails"; // Thymeleaf template name for displaying user details
    }

    @GetMapping("/users/new")
    public String createNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "createUser"; // Thymeleaf template name for creating a new user
    }

    @PostMapping("/users/new")
    public String createNewUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "editUser"; // Thymeleaf template name for editing user details
    }

    @PostMapping("/users/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute User updatedUser) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            // Update user fields
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            // Update other fields as needed

            userRepository.save(user);
        }
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }
    
    
}
