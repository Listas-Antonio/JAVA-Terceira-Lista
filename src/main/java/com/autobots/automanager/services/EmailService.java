package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entity.Email;
import com.autobots.automanager.repository.EmailRepository;


@Service
public class EmailService {

	@Autowired
	private EmailRepository repository;
	
	public List<Email> findAll(){
		return repository.findAll();
	}
	
	public Email findById(Long id) {
		Optional<Email> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado", null));
	}
	
	public Email insert(Email obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Email update(Email obj) {
		Email newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Email newObj, Email obj) {
		newObj.setAddress(obj.getAddress());
	}
	
	
}
