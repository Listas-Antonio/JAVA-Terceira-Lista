package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entity.Merchandise;
import com.autobots.automanager.repository.MerchandiseRepository;


@Service
public class MerchandiseService {

	@Autowired
	private MerchandiseRepository repository;
	
	public List<Merchandise> findAll(){
		return repository.findAll();
	}
	
	public Merchandise findById(Long id) {
		Optional<Merchandise> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado", null));
	}
	
	public Merchandise insert(Merchandise obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Merchandise update(Merchandise obj) {
		Merchandise newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Merchandise newObj, Merchandise obj) {
		newObj.setDescription(obj.getDescription());
		newObj.setExpiration(obj.getExpiration());
		newObj.setManufacturing(obj.getManufacturing());
		newObj.setName(obj.getName());
		newObj.setQuantity(obj.getQuantity());
		newObj.setRegistration(obj.getRegistration());
		newObj.setValue(obj.getValue());
	}
	
	
}
