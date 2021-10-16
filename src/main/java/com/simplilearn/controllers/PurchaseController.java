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

import com.simplilearn.entities.Product;
import com.simplilearn.entities.Purchase;
import com.simplilearn.repositories.CategoryRepository;
import com.simplilearn.repositories.ProductRepository;
import com.simplilearn.repositories.PurchaseRepository;
import com.simplilearn.repositories.UserRepository;

@Controller
public class PurchaseController {
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
		
    @GetMapping("/purchases")
    public String showPurchaseList(Model model) {
        model.addAttribute("purchases", this.purchaseRepository.findAll());
        return "purchases";
    }
    
	@RequestMapping(value="/addPurchase", method=RequestMethod.POST)
	public String savePurchase(@Valid @ModelAttribute("purchase") Purchase purchase, ModelMap model) {
		purchaseRepository.save(purchase);
	    model.addAttribute("purchases", purchaseRepository.findAll());
	    return "purchases";
	}
	@RequestMapping(value="/addPurchase")
	public String addPurchase(ModelMap model) {
	    model.addAttribute("purchase", new Purchase());
	    model.addAttribute("productCategories", productRepository.findAll());
	    model.addAttribute("userCategories", userRepository.findAll());

	    return "add-purchase";
	}
    @GetMapping("/deletePurchase/{id}")
    public String deletePurchase(@PathVariable("id") long id, Model model) {
        Purchase purchase = this.purchaseRepository.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid Purchase Id:" + id));
        this.purchaseRepository.delete(purchase);
        return "redirect:/purchases";
    }
}
