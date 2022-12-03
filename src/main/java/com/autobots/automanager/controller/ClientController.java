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

import com.autobots.automanager.entity.Client;
import com.autobots.automanager.models.ClientLinkAdder;
import com.autobots.automanager.services.ClientService;



@RestController
@RequestMapping("/client")
public class ClientController {
	
	@Autowired
	private ClientService service;
	
	@Autowired
	private ClientLinkAdder linkAdder;
	
	@GetMapping("/clients")
	public ResponseEntity<List<Client>> getAllClients(){
		List<Client> allClients = service.findAll();
		if(allClients.isEmpty()) {
			ResponseEntity<List<Client>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			linkAdder.addLink(allClients);
			
			ResponseEntity<List<Client>> response = new ResponseEntity<>(allClients, HttpStatus.ACCEPTED);
			return response;
		}
	}
	
	@GetMapping("/client/{id}")
	public ResponseEntity<Client> getClient(@PathVariable Long id){
		Client client = service.findById(id);
		if(client == null) {
			ResponseEntity<Client> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			linkAdder.addLink(client);
			ResponseEntity<Client> response = new ResponseEntity<>(client, HttpStatus.FOUND);
			return response;
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> insertNewClient(@RequestBody Client obj){


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
	public ResponseEntity<?> updateClient(@RequestBody Client obj){
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
	public ResponseEntity<?> deleteClient(@PathVariable Long id){
		HttpStatus status;
		String responseString;
		
		Client client = service.findById(id);
		
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
