package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entity.User;
import com.autobots.automanager.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado", null));
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setAddress(obj.getAddress());
		newObj.setCredentials(obj.getCredentials());
		newObj.setDocuments(obj.getDocuments());
		newObj.setEmails(obj.getEmails());
		newObj.setMerchandise(obj.getMerchandise());
		newObj.setName(obj.getName());
		newObj.setProfiles(obj.getProfiles());
		newObj.setSales(obj.getSales());
		newObj.setSocialName(obj.getSocialName());
		newObj.setTelephones(obj.getTelephones());
		newObj.setVehicles(obj.getVehicles());
	}
	
}
