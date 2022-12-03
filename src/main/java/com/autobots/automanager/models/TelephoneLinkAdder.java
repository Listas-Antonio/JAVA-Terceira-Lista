package com.autobots.automanager.models;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.ClientController;
import com.autobots.automanager.controller.TelephoneController;
import com.autobots.automanager.entity.Client;
import com.autobots.automanager.entity.Telephone;

@Component
public class TelephoneLinkAdder implements LinkAdder<Telephone>{

	@Override
	public void addLink(List<Telephone> baseList) {
		for(Telephone obj : baseList) {
			long id = obj.getId();
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(TelephoneController.class)
							.getTelephone(id))
					.withSelfRel();
			obj.add(selfLink);
		}
	}
	
	@Override
	public void addLink(Telephone object) {
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(TelephoneController.class)
						.getAllTelephones())
				.withRel("telephones");
		object.add(selfLink);
	}	

}
