package com.test.rest.webservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.test.rest.webservice.bean.User;
import com.test.rest.webservice.bean.dao.UserDaoService;
import com.test.rest.webservice.exception.UserNotFoundException;
import com.test.rest.webservice.repository.UserRepository;

@RestController
@RequestMapping("jpa/")
public class UserJPAController {

	@Autowired
	private UserRepository repository;

	@GetMapping("users")
	public List<User> retriveAllUser() {
		return repository.findAll();
	}

	@GetMapping("users/{id}")
	public EntityModel<User> retriveUser(@PathVariable("id") int id) {
		Optional<User> user = repository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		EntityModel<User> entityModel = EntityModel.of(user.get());
		WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).retriveAllUser());
		entityModel.add(linkBuilder.withRel("all-users"));
		return entityModel;

	}

	@PostMapping("users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User savedUser = repository.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("users/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		repository.deleteById(id);
	}
}
