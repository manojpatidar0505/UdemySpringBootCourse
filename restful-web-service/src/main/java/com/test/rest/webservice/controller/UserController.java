package com.test.rest.webservice.controller;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test.rest.webservice.bean.User;
import com.test.rest.webservice.bean.dao.UserDaoService;
import com.test.rest.webservice.exception.UserNotFoundException;

@RestController
public class UserController {
	@Autowired
	private UserDaoService daoService;

	@GetMapping("users")
	public List<User> retriveAllUser() {
		return daoService.findAll();
	}

	@GetMapping("users/{id}")
	public EntityModel<User> retriveUser(@PathVariable("id") int id) {
		User user= daoService.findOne(id);
		if(user==null) {
			throw new UserNotFoundException("id-"+id);
		}
		EntityModel<User> entityModel=EntityModel.of(user);
		WebMvcLinkBuilder linkBuilder=linkTo(methodOn(this.getClass()).retriveAllUser());
		entityModel.add(linkBuilder.withRel("all-users"));
		return entityModel;

	}
	@PostMapping("users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser=daoService.save(user);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		User user= daoService.deleteById(id);
		if(user==null) {
			throw new UserNotFoundException("id-"+id);
		}
		
	}
}
