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

import com.autobots.automanager.entity.Document;
import com.autobots.automanager.models.DocumentLinkAdder;
import com.autobots.automanager.services.DocumentService;





@RestController
@RequestMapping("/document")
public class DocumentController {
	
	@Autowired
	private DocumentService service;
	
	@Autowired
	private DocumentLinkAdder linkAdder;
	
	@GetMapping("/documents")
	public ResponseEntity<List<Document>> getAllDocument(){
		List<Document> allDocuments = service.findAll();
		if(allDocuments.isEmpty()) {
			ResponseEntity<List<Document>> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		}else {
			linkAdder.addLink(allDocuments);
			ResponseEntity<List<Document>> response = new ResponseEntity<>(allDocuments, HttpStatus.ACCEPTED);
			return response;
		}
	}
	
	@GetMapping("/document/{id}")
	public ResponseEntity<Document> getDocument(@PathVariable Long id){
		List<Document> allDocuments = service.findAll();
		Document document = service.findById(id);
		if(document == null) {
			ResponseEntity<Document> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			return response;
		} else {
			linkAdder.addLink(document);
			ResponseEntity<Document> response = new ResponseEntity<>(document, HttpStatus.FOUND);
			return response;
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> insertNewDocument(@RequestBody Document obj){
		
		HttpStatus status;
		String responseString;
		
		if(obj.getId() == null) {
			
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
	public ResponseEntity<?> updateDocument(@RequestBody Document obj){
		
		HttpStatus status;
		String responseString;
		
		if(obj.getId() == null) {
			
			responseString = "Object cannot be null";
			status = HttpStatus.NOT_FOUND;
			
		}else {
			
			responseString = "Successful request";
			status = HttpStatus.ACCEPTED;
			
			service.update(obj);
		}
		
		return new ResponseEntity<>(responseString, status);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteDocument(@PathVariable Long id){

		HttpStatus status;
		String responseString;
		
		Document obj = service.findById(id);
		
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
