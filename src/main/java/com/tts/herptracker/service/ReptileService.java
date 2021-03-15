package com.tts.herptracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tts.herptracker.model.Reptile;
import com.tts.herptracker.model.User;
import com.tts.herptracker.repository.ReptileRepository;

@Service
public class ReptileService {
	
	@Autowired
	private ReptileRepository reptileRepository;
	
	
	public List<Reptile> findAllByUser(User user) {
		List<Reptile> reptiles = reptileRepository.findAllByUser(user);
		return reptiles;
		
	}
	
	public void save(Reptile reptile) {
		reptileRepository.save(reptile);
	}

}
