package com.autobots.automanager.models;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.AddressController;
import com.autobots.automanager.controller.ClientController;
import com.autobots.automanager.controller.TelephoneController;
import com.autobots.automanager.entity.Address;
import com.autobots.automanager.entity.Client;
import com.autobots.automanager.entity.Telephone;

@Component
public class AddressLinkAdder implements LinkAdder<Address>{

	@Override
	public void addLink(List<Address> baseList) {
		for(Address obj : baseList) {
			long id = obj.getId();
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(AddressController.class)
							.getAddress(id))
					.withSelfRel();
			obj.add(selfLink);
		}
	}
	
	@Override
	public void addLink(Address object) {
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(AddressController.class)
						.getAllAddresses())
				.withRel("addresses");
		object.add(selfLink);
	}	

}
