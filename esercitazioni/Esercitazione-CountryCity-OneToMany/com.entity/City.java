package com.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;

/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@NamedQuery(name = "City.findAll", query = "SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	private String city;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	private Integer cityId;

	@UpdateTimestamp
	@Column(name = "last_update")
	private Timestamp lastUpdate;

	// bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	public City() {
	}

	// per insert
	public City(String city) {
		super();
		this.city = city;
	}

	// per update
	public City(String city, Integer cityId) {
		super();
		this.city = city;
		this.cityId = cityId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getCityId() {
		return this.cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "City\n[cityId=" + cityId + ",\n\tcity=" + city + ",\n\tcountryId=" + country.getCountryId()
				+ ",\n\tlastUpdate=" + lastUpdate + "]";
	}

}