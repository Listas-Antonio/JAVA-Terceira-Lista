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

import com.autobots.automanager.entity.Telephone;
import com.autobots.automanager.models.TelephoneLinkAdder;
import com.autobots.automanager.services.TelephoneService;





@RestController
@RequestMapping("/telephone")
public class TelephoneController {
	
	@Autowired
	private TelephoneService service;
	

	@Autowired
	private TelephoneLinkAdder linkAdder;
	
	@GetMapping("/telephones")
	public ResponseEntity<List<Telephone>> getAllTelephones(){
		List<Telephone> allTelephones = service.findAll();
		if(allTelephones.isEmpty()) {
			ResponseEntity<List<Telephone>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			linkAdder.addLink(allTelephones);
			ResponseEntity<List<Telephone>> response = new ResponseEntity<>(allTelephones, HttpStatus.ACCEPTED);
			return response;
		}
	}
	
	@GetMapping("/telephone/{id}")
	public ResponseEntity<Telephone> getTelephone(@PathVariable Long id){
		List<Telephone> allTelephones = service.findAll();
		Telephone telephone = service.findById(id);
		if(telephone == null) {
			ResponseEntity<Telephone> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			linkAdder.addLink(telephone);
			ResponseEntity<Telephone> response = new ResponseEntity<>(telephone, HttpStatus.FOUND);
			return response;
		}

	}
	
	@PostMapping("/register")
	public ResponseEntity<?> insertNewTelephone(@RequestBody Telephone obj){

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
	public ResponseEntity<?> updateTelephone(@RequestBody Telephone obj){
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
	public ResponseEntity<?> deleteTelephone(@PathVariable Long id){
		HttpStatus status;
		String responseString;
		
		Telephone telephone = service.findById(id);
		
		if(telephone.getId() == null) {
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
