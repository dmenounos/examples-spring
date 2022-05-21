package com.example.template.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.example.template.model.Entity;

@Repository
public class Database {

	/** In memory database. */
	private final Map<String, Entity> records = new HashMap<>();

	public Map<String, Entity> getRecords() {
		return records;
	}

	{
		Entity entity = new Entity();
		entity.setName("Entity_1");
		entity.setCountry(Entity.Country.US);
		entity.setIncorporationDate(new Date());
		entity.getShareholders().put("Share_Holder_A", 1000);
		entity.getShareholders().put("Share_Holder_B", 2000);

		records.put(entity.getName(), entity);
	}
}
