package com.example.template.service;

import java.util.List;

import com.example.template.model.Entity;

/**
 * Management interface for {@link Entity} objects.
 */
public interface EntityService {

	/**
	 * Finds all the {@link Entity} objects.
	 */
	List<Entity> findAll();

	/**
	 * Finds a {@link Entity} object by matching its name property.
	 */
	Entity findByName(String name);

	/**
	 * Stores a {@link Entity} object. It has to be valid.
	 * 
	 * @throws {@link ServiceException} when entity is not valid.
	 * @throws {@link ServiceException} when entity already exists.
	 */
	Entity create(Entity entity);

	/**
	 * Updates a {@link Entity} object. It has to be valid.
	 * 
	 * @throws {@link ServiceException} when entity is not valid.
	 * @throws {@link ServiceException} when entity does not exist.
	 */
	Entity update(Entity entity);

	/**
	 * Removes a {@link Entity} object from the data store.
	 */
	void delete(String name);
}
