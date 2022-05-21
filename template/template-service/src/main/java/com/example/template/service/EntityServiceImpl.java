package com.example.template.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.template.model.Entity;

@Service
public class EntityServiceImpl implements EntityService {

	@Autowired
	private Database database;

	@Override
	public List<Entity> findAll() {
		return new ArrayList<Entity>(getEntities().values());
	}

	@Override
	public Entity findByName(String name) {
		return getEntities().get(name);
	}

	@Override
	public Entity create(Entity entity) {
		validate(entity);

		if (getEntities().containsKey(entity.getName())) {
			throw new ServiceException("entity already exists");
		}

		getEntities().put(entity.getName(), entity);
		return entity;
	}

	@Override
	public Entity update(Entity entity) {
		validate(entity);

		if (!getEntities().containsKey(entity.getName())) {
			throw new ServiceException("entity does not exist");
		}

		getEntities().put(entity.getName(), entity);
		return entity;
	}

	@Override
	public void delete(String name) {
		getEntities().remove(name);
	}

	/**
	 * Convenience method.
	 * 
	 * @return the {@link Database#records} collection.
	 */
	private Map<String, Entity> getEntities() {
		return database.getRecords();
	}

	/**
	 * Checks whether a {@link Entity} object is valid.
	 * 
	 * @throws {@link ServiceException} when it is not.
	 */
	private void validate(Entity entity) {
		if (entity == null) {
			throw new ServiceException("entity is null");
		}

		if (entity.getName() == null || entity.getName().isEmpty()) {
			throw new ServiceException("entity.name is empty");
		}

		if (entity.getIncorporationDate() == null) {
			throw new ServiceException("entity.incorporationDate is null");
		}

		if (entity.getCountry() == null) {
			throw new ServiceException("entity.country is null");
		}

		if (entity.getTotalShares() < 0) {
			throw new ServiceException("entity.totalShares is negative");
		}
	}
}
