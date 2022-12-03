package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entity.User;
import com.autobots.automanager.entity.Credential;
import com.autobots.automanager.repository.CredentialRepository;

@Service
public class CredentialService {

	@Autowired
	private CredentialRepository repository;
	
	public List<Credential> findAll(){
		return repository.findAll();
	}
	
	public Credential findById(Long id) {
		Optional<Credential> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado", null));
	}
	
	public Credential insert(Credential obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Credential update(Credential obj) {
		Credential newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Credential newObj, Credential obj) {
		newObj.setCreationDate(obj.getCreationDate());
		newObj.setInactive(obj.isInactive());
		newObj.setLastAccess(obj.getLastAccess());	
	}
	
	
}
