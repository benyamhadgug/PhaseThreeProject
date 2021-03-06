package com.simplilearn.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.simplilearn.entities.User;
import com.simplilearn.services.UserService;

@Controller
public class UserController {
    
	@Autowired
	private UserService userService;
    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }
    
    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        
        this.userService.save(user);
        return "redirect:/users";
    }
    @GetMapping("/users")
    public String showUserList(Model model) {
        model.addAttribute("users", this.userService.findAll());
        return "users";
    }
    @GetMapping("/editUser/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = this.userService.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        
        model.addAttribute("user", user);
        return "update-user";
    }
    @PostMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }
            
        this.userService.save(user);
        return "redirect:/users";
    }
        
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = this.userService.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        this.userService.delete(user);
        return "redirect:/users";
    }
}