package controller;


import java.util.HashMap;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exception.ResourceNotFoundException;
import models.User;
import repository.UserRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")

public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	// get all employees
	@GetMapping("/t_users")
	public List<User> getAllUsers(){
		return userRepository.findAll();
		
	}
	// create employee rest api
	@PostMapping("/t_users")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	// get employee by id rest api
	@GetMapping("/t_users/{id}")
	public ResponseEntity<User> getUsersByUserName(@PathVariable String userName) {
		User user = userRepository.findById(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + userName));
		return ResponseEntity.ok(user);
		
	}
	
	// update employee rest api
	
	@PutMapping("/t_users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable String userName, @RequestBody User userDetails){
		User user = userRepository.findById(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + userName));
		
		user.setUserName(userDetails.getUserName());
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmailId(userDetails.getEmailId());
		user.setPhoneId(userDetails.getPhoneId());
		user.setCreatedTime(userDetails.getCreatedTime());
		user.setModifiedTime(userDetails.getModifiedTime());
		user.setPassword(userDetails.getPassword());
		
		User updatedUser = userRepository.save(user);
		return ResponseEntity.ok(updatedUser);
	}
	
	// delete employee rest api
	@DeleteMapping("/t_users/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable String userName){
		User user = userRepository.findById(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with username :" + userName));
		
		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
