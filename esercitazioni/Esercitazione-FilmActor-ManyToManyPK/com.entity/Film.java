package com.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@NamedQuery(name = "Film.findAll", query = "SELECT f FROM Film f")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "film_id")
	private Integer filmId;

	@Lob
	private String description;

	@Column(name = "language_id")
	private Integer languageId;

	@UpdateTimestamp
	@Column(name = "last_update")
	private Timestamp lastUpdate;

	private Integer length;

//	@Column(name = "original_language_id")
//	private byte originalLanguageId;

	private String rating;

	@Temporal(TemporalType.DATE)
	@Column(name = "release_year")
	private Date releaseYear;

	@Column(name = "rental_duration")
	private Integer rentalDuration;

	@Column(name = "rental_rate")
	private Double rentalRate;

	@Column(name = "replacement_cost")
	private Double replacementCost;

	@Column(name = "special_features")
	private Object specialFeatures;

	private String title;

	// bi-directional many-to-many association to Actor
	@ManyToMany
//	@JoinColumn(name = "film_id")
	private List<Actor> actors = new ArrayList<>();

	public Film() {
	}

	// x insert
	// che palle ste NN : 1, 7, 0.99, 14.99
	public Film(Integer languageId, Integer rentalDuration, Double rentalRate, Double replacementCost, String title) {
		super();
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.replacementCost = replacementCost;
		this.title = title;
	}

	// x update
	public Film(Integer filmId, Integer languageId, Integer rentalDuration, Double rentalRate, Double replacementCost,
			String title) {
		super();
		this.filmId = filmId;
		this.languageId = languageId;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.replacementCost = replacementCost;
		this.title = title;
	}

	public Integer getFilmId() {
		return this.filmId;
	}

	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Integer getLength() {
		return this.length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

//	public byte getOriginalLanguageId() {
//		return this.originalLanguageId;
//	}
//
//	public void setOriginalLanguageId(byte originalLanguageId) {
//		this.originalLanguageId = originalLanguageId;
//	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Date getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(Date releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Integer getRentalDuration() {
		return this.rentalDuration;
	}

	public void setRentalDuration(Integer rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public Double getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(Double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public Double getReplacementCost() {
		return this.replacementCost;
	}

	public void setReplacementCost(Double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public Object getSpecialFeatures() {
		return this.specialFeatures;
	}

	public void setSpecialFeatures(Object specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Actor> getActors() {
		return this.actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	@Override
	public String toString() {
		return "Film [title=" + title + "]";
	}

}