package com.example.template.model;

import java.util.Date;

import com.example.template.model.Entity.Country;

/**
 * Builder of Entity objects.
 */
public class EntityBuilder {

	private Entity prototype = new Entity();

	public EntityBuilder setName(String name) {
		prototype.setName(name);
		return this;
	}

	public EntityBuilder setCountry(Country country) {
		prototype.setCountry(country);
		return this;
	}

	public EntityBuilder setIncorporationDate(Date incorporationDate) {
		prototype.setIncorporationDate(incorporationDate);
		return this;
	}

	public EntityBuilder addShareholder(String shareholderName, Integer noShares) {
		prototype.getShareholders().put(shareholderName, noShares);
		return this;
	}

	public Entity build() {
		return new Entity(prototype);
	}
}
