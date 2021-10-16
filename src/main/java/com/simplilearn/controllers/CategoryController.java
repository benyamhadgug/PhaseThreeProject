package com.simplilearn.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.simplilearn.entities.Category;
import com.simplilearn.entities.User;
import com.simplilearn.services.CategoryService;
import com.simplilearn.services.UserService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

    @GetMapping("/signupCategory")
    public String showSignUpForm(Category user) {
        return "add-category";
    }
    @PostMapping("/addCategory")
    public String addUser(@Valid Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-category";
        }
        
        this.categoryService.save(category);
        return "redirect:/categories";
    }
    @GetMapping("/categories")
    public String showUserList(Model model) {
        model.addAttribute("categories", this.categoryService.findAll());
        return "categories";
    }
    @GetMapping("/editCategory/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Category category = this.categoryService.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid Category Id:" + id));
        
        model.addAttribute("category", category);
        return "update-category";
    }
    @PostMapping("/updateCategory/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid Category user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-category";
        }
            
        this.categoryService.save(user);
        return "redirect:/categories";
    }
        
    @GetMapping("/deleteCategory/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Category user = this.categoryService.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid Category Id:" + id));
        this.categoryService.delete(user);
        return "redirect:/categories";
    }
}
