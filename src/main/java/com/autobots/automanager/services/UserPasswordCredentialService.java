package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entity.UserPasswordCredential;
import com.autobots.automanager.repository.UserPasswordCredentialRepository;


@Service
public class UserPasswordCredentialService {

	@Autowired
	private UserPasswordCredentialRepository repository;
	
	public List<UserPasswordCredential> findAll(){
		return repository.findAll();
	}
	
	public UserPasswordCredential findById(Long id) {
		Optional<UserPasswordCredential> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado", null));
	}
	
	public UserPasswordCredential insert(UserPasswordCredential obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public UserPasswordCredential update(UserPasswordCredential obj) {
		UserPasswordCredential newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(UserPasswordCredential newObj, UserPasswordCredential obj) {
		newObj.setPassword(obj.getPassword());
		newObj.setUsername(obj.getPassword());
	}
	
	
}
