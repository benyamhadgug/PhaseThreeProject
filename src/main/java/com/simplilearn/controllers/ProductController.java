package com.simplilearn.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.simplilearn.entities.Category;
import com.simplilearn.entities.Product;
import com.simplilearn.repositories.CategoryRepository;
import com.simplilearn.repositories.ProductRepository;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
    @GetMapping("/products")
    public String showUserList(Model model) {
        model.addAttribute("products", this.productRepository.findAll());
        return "products";
    }
    
	@RequestMapping(value="/addProduct", method=RequestMethod.POST)
	public String saveProduct(@Valid @ModelAttribute("product") Product product, ModelMap model) {
		productRepository.save(product);
	    model.addAttribute("products", productRepository.findAll());
	    return "products";
	}
	@RequestMapping(value="/addProduct")
	public String addProduct(ModelMap model) {
	    model.addAttribute("product", new Product());
	    model.addAttribute("productCategories", categoryRepository.findAll());
	    return "add-product";
	}
    @GetMapping("/deleteProduct/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Product user = this.productRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid Product Id:" + id));
        this.productRepository.delete(user);
        return "redirect:/products";
    }
}
