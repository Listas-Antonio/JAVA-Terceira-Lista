package com.autobots.automanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entity.User;
import com.autobots.automanager.models.UserLinkAdder;
import com.autobots.automanager.services.UserService;



@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserLinkAdder linkAdder;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> allClients = service.findAll();
		if(allClients.isEmpty()) {
			ResponseEntity<List<User>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			linkAdder.addLink(allClients);
			
			ResponseEntity<List<User>> response = new ResponseEntity<>(allClients, HttpStatus.ACCEPTED);
			return response;
		}
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id){
		User client = service.findById(id);
		if(client == null) {
			ResponseEntity<User> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			linkAdder.addLink(client);
			ResponseEntity<User> response = new ResponseEntity<>(client, HttpStatus.FOUND);
			return response;
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> insertNewUser(@RequestBody User obj){


		HttpStatus status;
		String responseString;
        
		if(obj.getId() == null){
        	
            status = HttpStatus.NOT_FOUND;
            responseString = "Body cannot be null";    
            
        }else {
        	
            status = HttpStatus.ACCEPTED;
            responseString = "Successful request";
            
        	service.insert(obj);
        }
        return new ResponseEntity<>(responseString, status);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody User obj){
		HttpStatus status;
		String responseString;
		
		if(obj.getId() == null) {
			
			responseString = "Body cannot be null";
			status = HttpStatus.NOT_FOUND;
			
		}else {
			
			responseString = "Successful request";
			status = HttpStatus.ACCEPTED;
			
			service.update(obj);
		}
		
		return new ResponseEntity<>(responseString, status);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id){
		HttpStatus status;
		String responseString;
		
		User client = service.findById(id);
		
		if(client.getId() == null) {
			status = HttpStatus.NOT_FOUND;
			responseString = "Object not found";
		}else {
			status = HttpStatus.ACCEPTED;
			responseString = "Successful request";
			service.delete(id);
			
		}
		
		return new ResponseEntity<>(responseString, status);
	}
}
