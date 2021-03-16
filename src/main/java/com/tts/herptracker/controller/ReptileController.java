package com.tts.herptracker.controller;

import java.util.List;

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
		model.addAttribute("newTrax", new FeedTrack());
        
		model.addAttribute("feedTrax", feedTrack);
		model.addAttribute(reptile);
		
		return "reptile";
	}
	
	@PostMapping("/reptile/{id}")
	public String feed(@PathVariable int id, Model model, BindingResult bindingResult, FeedTrack feedTrax) {
		Reptile reptile = reptileService.findById(id);
		
		if (!bindingResult.hasErrors()) {
			feedTrax.setReptile(reptile);
            feed.save(feedTrax);
            
            model.addAttribute("newReptile", new Reptile());
        }
		
		
		
		return "reptile";
	}

}
