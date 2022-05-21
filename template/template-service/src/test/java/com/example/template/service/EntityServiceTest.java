package com.example.template.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.template.model.Entity;
import com.example.template.model.EntityBuilder;

@SpringBootTest
public class EntityServiceTest {

	@Autowired
	private EntityService entityService;

	@Autowired
	private Database database;

	@BeforeEach
	public void init() {
		database.getRecords().clear();
	}

	@Test
	public void createNull_ShouldThrow() {

		// Attempt to create a null reference
		assertThrows(ServiceException.class, () -> {
			entityService.create(null);
		});
	}

	@Test
	public void createInvalid_ShouldThrow() {

		// Attempt to create an incomplete Entity
		assertThrows(ServiceException.class, () -> {
			entityService.create(new Entity());
		});
	}

	@Test
	public void createDuplicate_ShouldThrow() {

		// Create a Entity
		entityService.create(createEntity());

		// Attempt to a create a duplicate Entity
		assertThrows(ServiceException.class, () -> {
			entityService.create(createEntity());
		});
	}

	@Test
	public void create() {

		// Create a Entity
		Entity entity = entityService.create(createEntity());
		assertNotNull(entity);
	}

	@Test
	public void updateNonExistent_ShouldThrow() {

		// Attempt to update a Entity that does not exist
		assertThrows(ServiceException.class, () -> {
			entityService.update(createEntity());
		});
	}

	@Test
	public void update() {

		// Prepare a Entity
		Entity entity1 = createEntity();
		database.getRecords().put(entity1.getName(), entity1);

		// Update the Entity
		Entity entity2 = createEntity();
		entity2.setCountry(Entity.Country.CH);
		entity2 = entityService.update(entity2);
		assertNotNull(entity2);
	}

	/*
	 * Convenience factory method.
	 */
	private static Entity createEntity() {
		return new EntityBuilder()
			.setName("Entity")
			.setCountry(Entity.Country.US)
			.setIncorporationDate(new Date())
			.addShareholder("Share Holder A", 1000)
			.addShareholder("Share Holder B", 2000)
			.build();
	}
}
