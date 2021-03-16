package com.tts.herptracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tts.herptracker.model.FeedTrack;
import com.tts.herptracker.model.Reptile;
import com.tts.herptracker.repository.FeedTrackRepository;

@Service
public class FeedTrackService {
	
	@Autowired
	private FeedTrackRepository repo;
	
	public List<FeedTrack> findAllByReptile(Reptile reptile) {
		List<FeedTrack> feedTrack = repo.findAllByReptile(reptile);
		return feedTrack;
	}
	
	public List<FeedTrack> findMostRecent(){
		return repo.findMostRecent();
	}
	
	
	public void save(FeedTrack feedTrack) {
		repo.save(feedTrack);
	}

}
