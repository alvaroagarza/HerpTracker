package com.tts.herptracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.tts.herptracker.model.Reptile;
import com.tts.herptracker.model.User;
import com.tts.herptracker.service.ReptileService;
import com.tts.herptracker.service.UserService;

@Controller
public class FeedController {
	
	@Autowired
	ReptileService reptileService;
	
	@Autowired
	UserService userService;
	
	  @GetMapping(value={"/" , "/feed"})
	    public String feed(){
	        return "feed";
	    }
	  
	  @GetMapping(value = "/newanimal")
	    public String getAnimalForm(Model model) {
	        model.addAttribute("newReptile", new Reptile());
	        return "newanimal";
	        
	    }
	  
	  
	    @PostMapping(value = "/feed")
	    public String submitAnimalForm(Reptile reptile, BindingResult bindingResult, Model model) {
	        User user = userService.getLoggedInUser();
	        if (!bindingResult.hasErrors()) {
	            reptile.setUser(user);
	            reptileService.save(reptile);
	            model.addAttribute("successMessage", "Animal added");
	            model.addAttribute("newReptile", new Reptile());
	        }
	        return "newanimal";
	  
	    }
	    
	  @ModelAttribute("reptiles")
		public List<Reptile> reptiles() {
		  User loggedInUser = userService.getLoggedInUser();
		 	return reptileService.findAllByUser(loggedInUser);
		}
	  
	  

}
