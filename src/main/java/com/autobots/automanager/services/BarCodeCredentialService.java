package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entity.BarCodeCredential;
import com.autobots.automanager.entity.User;
import com.autobots.automanager.repository.BarCodeCredentialRepository;

@Service
public class BarCodeCredentialService {

	@Autowired
	private BarCodeCredentialRepository repository;
	
	public List<BarCodeCredential> findAll(){
		return repository.findAll();
	}
	
	public BarCodeCredential findById(Long id) {
		Optional<BarCodeCredential> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado", null));
	}
	
	public BarCodeCredential insert(BarCodeCredential obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public BarCodeCredential update(BarCodeCredential obj) {
		BarCodeCredential newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(BarCodeCredential newObj, BarCodeCredential obj) {
		newObj.setCod(obj.getCod());
	}
	
	
}
