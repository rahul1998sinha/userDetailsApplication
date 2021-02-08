package com.rakuten.training.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.User;
import com.rakuten.training.service.UserService;

@RestController
public class UserController {
	@Autowired
	UserService service;
	
	@GetMapping("/users")
	public List<User> findAllUsers(){
		return service.findAllUsers();
	}
	
	@GetMapping("/users/{idPathVariable}")
	public User findUserById(@PathVariable("idPathVariable") int id)
	{
		User u = service.findUserById(id);
		return u;
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> registerUser(@RequestBody User newUser){
		try {
			User u = service.registerUser(newUser);
			if(u==null)
				return  new ResponseEntity<User>(HttpStatus.CONFLICT);
			return new ResponseEntity<User>(newUser,HttpStatus.BAD_REQUEST);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}
	}
	

}
