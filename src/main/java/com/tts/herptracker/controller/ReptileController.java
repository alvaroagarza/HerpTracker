package com.tts.herptracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.tts.herptracker.model.FeedTrack;
import com.tts.herptracker.model.Reptile;
import com.tts.herptracker.service.FeedTrackService;
import com.tts.herptracker.service.ReptileService;


@Controller
public class ReptileController {
	
	@Autowired
	ReptileService reptileService;
	
	@Autowired
	FeedTrackService feed;
	
	@GetMapping("/reptile/{id}")
	public String show(@PathVariable int id, Model model) {
		Reptile reptile = reptileService.findById(id);
		List<FeedTrack> feedTrack = feed.findAllByReptile(reptile);
		String url = "/edit/" + id;
		model.addAttribute("newTrax", new FeedTrack());
        model.addAttribute("editLink", url);
		model.addAttribute("feedTrax", feedTrack);
		model.addAttribute("reptile", reptile);
		
		return "reptile";
	}
	
	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
	    Reptile reptile = reptileService.findById(id);
	    model.addAttribute("reptileUD", reptile);
	    return "update-reptile";
	}
	
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Valid Reptile reptile, Model model) {
		reptileService.updateWeightAndAge(id, reptile.getAge(), reptile.getWeight());
		
		
		
	    return "redirect:/";
	}
	
	@PostMapping("/reptile/{id}")
	public String feed(@PathVariable int id, Model model,  @Valid FeedTrack feedTrax) {
		Reptile reptile = reptileService.findById(id);
			String url = "/reptile/" + id;
			feedTrax.setReptile(reptile);
            feed.save(feedTrax);
            model.addAttribute("url", url);
            model.addAttribute("newReptile", new Reptile());
       
		
		
		
		return "success";
	}

}
