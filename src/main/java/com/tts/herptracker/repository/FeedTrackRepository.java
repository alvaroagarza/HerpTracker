package com.tts.herptracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.herptracker.model.FeedTrack;
import com.tts.herptracker.model.Reptile;

@Repository
public interface FeedTrackRepository extends CrudRepository<FeedTrack, Long> {
	
	List<FeedTrack> findAllByReptile(Reptile reptile);
	FeedTrack findById(long id);
	
	@Query(value = "SELECT FOOD, FEDAT FROM FEED_TRACK WHERE REPTILE = 3 LIMIT 10",
			nativeQuery = true)
    List<FeedTrack> findMostRecent();

}
