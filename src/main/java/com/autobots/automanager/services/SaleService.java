package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entity.Sale;
import com.autobots.automanager.repository.SaleRepository;


@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public List<Sale> findAll(){
		return repository.findAll();
	}
	
	public Sale findById(Long id) {
		Optional<Sale> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado", null));
	}
	
	public Sale insert(Sale obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Sale update(Sale obj) {
		Sale newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Sale newObj, Sale obj) {
		newObj.setClient(obj.getClient());
		newObj.setEmployee(obj.getEmployee());
		newObj.setIdentification(obj.getIdentification());
		newObj.setMerchandises(obj.getMerchandises());
		newObj.setRegistrationDate(obj.getRegistrationDate());
		newObj.setServices(obj.getServices());
		newObj.setVehicle(obj.getVehicle());
	}
	
	
}
