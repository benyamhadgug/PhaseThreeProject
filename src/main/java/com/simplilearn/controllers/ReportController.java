package com.simplilearn.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.simplilearn.entities.Purchase;
import com.simplilearn.repositories.PurchaseRepository;
import com.simplilearn.repositories.UserRepository;

@Controller
public class ReportController {
	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private UserRepository userRepository;
	
    @RequestMapping(value="/", method = RequestMethod.GET)
    String index(){
        System.out.println("..............hit");
        return "index";
    }
    
    @GetMapping("/report")
	public String showPurchaseList(Model model/* , HttpServletRequest request */) {
		/*
		 * List<Purchase> purchases= this.purchaseRepository.findAll(); Long
		 * userId=null; try { userId= Long.valueOf(request.getParameter("userId")); }
		 * catch(Exception e) {
		 * 
		 * } if(userId != null) purchases= this.purchaseRepository.findByUserId(userId);
		 * else
		 */
    	model.addAttribute("purchases", this.purchaseRepository.findAll());
        model.addAttribute("userCategories", this.userRepository.findAll());
        return "report";
    }
    @PostMapping("/report")
    public String postPurchaseList(Model model, HttpServletRequest request) {
    	List<Purchase> purchases= this.purchaseRepository.findAll();
    	Long userId=null;
    	try {
    		userId= Long.valueOf(request.getParameter("userId"));
    	} catch(Exception e) {
    		
    	}
    	System.out.println("user id : " + userId);
    	if(userId != null) {
    		final Long dUsrId= userId;
    		//purchases= this.purchaseRepository.findByUserId(userId);
    		System.out.println("Filtering for : " + userId);
    		purchases= (List<Purchase>) purchases.stream().filter(a->a.getUser().getId().equals(dUsrId)).collect(Collectors.toList());
    	}
    	else 
	    	model.addAttribute("purchases", purchases );
	        model.addAttribute("userCategories", this.userRepository.findAll());
        return "report";
    }
}
