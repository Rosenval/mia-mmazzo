package com.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
	private Integer countryId;

	@Column(name = "country")
	private String country;

	@UpdateTimestamp
	@Column(name = "last_update")
	private Timestamp lastUpdate;

	// bi-directional many-to-one association to City
	@OneToMany(mappedBy = "country")
	private List<City> cities;

	public Country() {
	}

	// per insert
	public Country(String country) {
		super();
		this.country = country;
	}

	// per update
	public Country(Integer countryId, String country) {
		super();
		this.countryId = countryId;
		this.country = country;
	}

	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<City> getCities() {
		return this.cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

//	public City addCity(City city) {
//		getCities().add(city);
//		city.setCountry(this);
//
//		return city;
//	}
//
//	public City removeCity(City city) {
//		getCities().remove(city);
//		city.setCountry(null);
//
//		return city;
//	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countryName=" + country + ", lastUpdate=" + lastUpdate + "]";
	}

}