package com.example.template.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.template.model.Entity;
import com.example.template.service.EntityService;

@RestController
@RequestMapping("/api/entities")
public class EntityController {

	@Autowired
	private EntityService entityService;

	@GetMapping
	public List<Entity> findAll() {
		return entityService.findAll();
	}

	@GetMapping("/{name}")
	public Entity findByName(@PathVariable String name) {
		Entity entity = entityService.findByName(name);

		if (entity == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		return entity;
	}

	@PostMapping
	public Entity create(@RequestBody Entity entity) {
		return entityService.create(entity);
	}

	@PutMapping
	public Entity update(@RequestBody Entity entity) {
		return entityService.update(entity);
	}

	@DeleteMapping("/{name}")
	public void delete(@PathVariable String name) {
		entityService.delete(name);
	}
}
