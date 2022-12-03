package com.autobots.automanager.models;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.ClientController;
import com.autobots.automanager.entity.Client;

@Component
public class ClientLinkAdder implements LinkAdder<Client>{

	@Override
	public void addLink(List<Client> baseList) {
		for(Client client : baseList) {
			long id = client.getId();
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(ClientController.class)
							.getClient(id))
					.withSelfRel();
			client.add(selfLink);
		}
	}
	
	@Override
	public void addLink(Client object) {
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ClientController.class)
						.getAllClients())
				.withRel("clients");
		object.add(selfLink);
	}	

}
