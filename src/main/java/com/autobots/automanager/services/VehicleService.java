package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entity.Vehicle;
import com.autobots.automanager.repository.VehicleRepository;


@Service
public class VehicleService {

	@Autowired
	private VehicleRepository repository;
	
	public List<Vehicle> findAll(){
		return repository.findAll();
	}
	
	public Vehicle findById(Long id) {
		Optional<Vehicle> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado", null));
	}
	
	public Vehicle insert(Vehicle obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Vehicle update(Vehicle obj) {
		Vehicle newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Vehicle newObj, Vehicle obj) {
		newObj.setLicensePlate(obj.getLicensePlate());
		newObj.setModel(obj.getModel());
		newObj.setOwner(obj.getOwner());
		newObj.setSales(obj.getSales());
		newObj.setVehicleType(obj.getVehicleType());
	}
	
	
}
