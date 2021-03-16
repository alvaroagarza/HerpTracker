package com.tts.herptracker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tts.herptracker.model.Reptile;
import com.tts.herptracker.model.User;

@Repository
public interface ReptileRepository extends CrudRepository<Reptile, Long>{
	List<Reptile> findAllByUser(User user);
	Reptile findById(long id);
	

}
