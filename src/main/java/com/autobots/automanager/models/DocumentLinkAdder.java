package com.autobots.automanager.models;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.AddressController;
import com.autobots.automanager.controller.ClientController;
import com.autobots.automanager.controller.DocumentController;
import com.autobots.automanager.controller.TelephoneController;
import com.autobots.automanager.entity.Address;
import com.autobots.automanager.entity.Client;
import com.autobots.automanager.entity.Document;
import com.autobots.automanager.entity.Telephone;

@Component
public class DocumentLinkAdder implements LinkAdder<Document>{

	@Override
	public void addLink(List<Document> baseList) {
		for(Document obj : baseList) {
			long id = obj.getId();
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(DocumentController.class)
							.getDocument(id))
					.withSelfRel();
			obj.add(selfLink);
		}
	}
	
	@Override
	public void addLink(Document object) {
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(DocumentController.class)
						.getAllDocument())
				.withRel("documents");
		object.add(selfLink);
	}	

}
