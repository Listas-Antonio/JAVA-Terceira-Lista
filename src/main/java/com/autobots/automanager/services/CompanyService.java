package com.autobots.automanager.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entity.Company;
import com.autobots.automanager.repository.CompanyRepository;


@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repository;
	
	public List<Company> findAll(){
		return repository.findAll();
	}
	
	public Company findById(Long id) {
		Optional<Company> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado", null));
	}
	
	public Company insert(Company obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public Company update(Company obj) {
		Company newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Company newObj, Company obj) {
		newObj.setAddress(obj.getAddress());
		newObj.setFantasyName(obj.getFantasyName());
		newObj.setMerchandises(obj.getMerchandises());
		newObj.setRegistrationDate(obj.getRegistrationDate());
		newObj.setSales(obj.getSales());
		newObj.setServices(obj.getServices());
		newObj.setSocialReason(obj.getSocialReason());
		newObj.setTelephones(obj.getTelephones());
		newObj.setUsers(obj.getUsers());
	}
	
	
}
