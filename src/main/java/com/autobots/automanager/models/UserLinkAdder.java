package com.autobots.automanager.models;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controller.UserController;
import com.autobots.automanager.entity.User;

@Component
public class UserLinkAdder implements LinkAdder<User>{

	@Override
	public void addLink(List<User> baseList) {
		for(User client : baseList) {
			long id = client.getId();
			Link selfLink = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(UserController.class)
							.getUser(id))
					.withSelfRel();
			client.add(selfLink);
		}
	}
	
	@Override
	public void addLink(User object) {
		Link selfLink = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(UserController.class)
						.getAllUsers())
				.withRel("users");
		object.add(selfLink);
	}	

}
