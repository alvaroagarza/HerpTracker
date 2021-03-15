package com.tts.herptracker.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;
	
	@Length(min = 3, message = "Your username must have at least 3 characters")
	@Length(max = 15, message = "Your username cannot have more than 15 characters")
	@Pattern(regexp="[^\\s]+", message="Your username cannot contain spaces")
	String username;

	@Length(min = 8, message = "Your password must have at least 5 characters")
	@Pattern(regexp="[^\\s]+", message="Your username cannot contain spaces")
	String password;
	
	private int active;

	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), 
	    inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

}
