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

import com.autobots.automanager.entity.Merchandise;
import com.autobots.automanager.services.MerchandiseService;


@RestController
@RequestMapping("/merchandise")
public class MerchandiseController {
	
	@Autowired
	private MerchandiseService service;
	

	
	@GetMapping("/merchandises")
	public ResponseEntity<List<Merchandise>> getAllMerchandises(){
		List<Merchandise> allObjects = service.findAll();
		if(allObjects.isEmpty()) {
			ResponseEntity<List<Merchandise>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		} else {

			
			ResponseEntity<List<Merchandise>> response = new ResponseEntity<>(allObjects, HttpStatus.ACCEPTED);
			return response;
		}
	}
	
	@GetMapping("/merchandise/{id}")
	public ResponseEntity<Merchandise> getMerchandise(@PathVariable Long id){
		Merchandise obj = service.findById(id);
		if(obj == null) {
			ResponseEntity<Merchandise> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		} else {

			ResponseEntity<Merchandise> response = new ResponseEntity<>(obj, HttpStatus.FOUND);
			return response;
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> insertNewMerchandise(@RequestBody Merchandise obj){


		HttpStatus status;
		String responseString;
        Long x = obj.getId();
		if(x == null){
        	
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
	public ResponseEntity<?> updateEmail(@RequestBody Merchandise obj){
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
	public ResponseEntity<?> deleteEmail(@PathVariable Long id){
		HttpStatus status;
		String responseString;
		
		Merchandise obj = service.findById(id);
		
		if(obj.getId() == null) {
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
