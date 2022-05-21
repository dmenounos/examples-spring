package com.example.template.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.stream.Collectors.summingInt;

/**
 * Entity object.
 */
public class Entity {

	public static enum Country {
		US, UK, CH
	}

	private String name;
	private Country country;
	private Date incorporationDate;
	private Map<String, Integer> shareholders = new HashMap<>();

	public Entity() {
	}

	public Entity(Entity other) {
		this.name = other.name;
		this.incorporationDate = other.incorporationDate;
		this.country = other.country;
		this.shareholders.putAll(other.shareholders);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Date getIncorporationDate() {
		return incorporationDate;
	}

	public void setIncorporationDate(Date incorporationDate) {
		this.incorporationDate = incorporationDate;
	}

	public int getTotalShares() {
		return shareholders.entrySet().stream()
			.collect(summingInt(Entry<String, Integer>::getValue));
	}

	public Map<String, Integer> getShareholders() {
		return shareholders;
	}

	@Override
	public String toString() {
		return "Entity { "
			+ "name=" + name + ", "
			+ "country=" + country + ", "
			+ "incorporationDate=" + incorporationDate + ", "
			+ "shareholders=" + shareholders + " "
			+ "}";
	}
}
