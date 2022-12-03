package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entity.ServiceEntity;
import com.autobots.automanager.repository.ServiceRepository;


@Service
public class ServiceService {

	@Autowired
	private ServiceRepository repository;
	
	public List<ServiceEntity> findAll(){
		return repository.findAll();
	}
	
	public ServiceEntity findById(Long id) {
		Optional<ServiceEntity> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado", null));
	}
	
	public ServiceEntity insert(ServiceEntity obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public ServiceEntity update(ServiceEntity obj) {
		ServiceEntity newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(ServiceEntity newObj, ServiceEntity obj) {
		newObj.setDescription(obj.getDescription());
		newObj.setName(obj.getName());
		newObj.setValue(obj.getValue());
	}
	
	
}
