package com.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Film.findAll", query = "SELECT f FROM Film f"),
		@NamedQuery(name = "Film.FilmsXActor", query = "select new com.vo.FilmsXActorVO(a.actorId, a.firstName, a.lastName, count(f)) as movies_x_actor from Actor a join a.films f group by a.actorId") })
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "film_id")
	private int filmId;

	@Lob
	private String description;

	@Column(name = "language_id")
	private byte languageId;

	@UpdateTimestamp
	@Column(name = "last_update")
	private Timestamp lastUpdate;

	private int length;

	@Column(name = "original_language_id")
	private byte originalLanguageId;

	private String rating;

	@Temporal(TemporalType.DATE)
	@Column(name = "release_year")
	private Date releaseYear;

	@Column(name = "rental_duration")
	private byte rentalDuration;

	@Column(name = "rental_rate")
	private BigDecimal rentalRate;

	@Column(name = "replacement_cost")
	private BigDecimal replacementCost;

	@Column(name = "special_features")
	private Object specialFeatures;

	private String title;

	// bi-directional many-to-many association to Actor
	@ManyToMany
//	@JoinColumn(name = "film_id")
	@JoinTable(name = "film_actor", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"), inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actor_id"))
	private List<Actor> actors;

	public Film() {
	}

	public int getFilmId() {
		return this.filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getLanguageId() {
		return this.languageId;
	}

	public void setLanguageId(byte languageId) {
		this.languageId = languageId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public byte getOriginalLanguageId() {
		return this.originalLanguageId;
	}

	public void setOriginalLanguageId(byte originalLanguageId) {
		this.originalLanguageId = originalLanguageId;
	}

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

	public byte getRentalDuration() {
		return this.rentalDuration;
	}

	public void setRentalDuration(byte rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public BigDecimal getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}

	public BigDecimal getReplacementCost() {
		return this.replacementCost;
	}

	public void setReplacementCost(BigDecimal replacementCost) {
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

}