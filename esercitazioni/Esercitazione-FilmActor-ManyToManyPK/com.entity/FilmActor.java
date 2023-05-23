package com.entity;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "film_actor")
public class FilmActor implements Serializable {

	private static final long serialVersionUID = 8519799605205717512L;

	// annotazione che indica che id e' una chiave primaria composta
	// e fa riferimento alla classe FilmActorPK
	@EmbeddedId
	private FilmActorPK filmActorPK;

	// @ManyToOne e @JoinColumn specificano la relazione many-to-one
	// tra FilmActor e Film e tra FilmActor e Actor
	// cioe': Film e Actor possono essere associati a molte istanze di FilmActor, ma
	// ogni istanza di FilmActor sara' univocamente associata a un singolo film
	// e un singolo attore

	// @MapsId("filmId") indica che il campo film mappa la colonna film_id nella
	// tabella di collegamento
	// ergo il valore di filmId nell'oggetto FilmActor corrisponde al valore della
	// chiave primaria film_id nella tabella di collegamento
	// (ovviamente stessa cosa vale per actor)

	// la classe utilizza quindi la chiave primaria composta FilmActorPK per
	// identificare univocamente le associazioni tra i film e gli attori nella
	// tabella di collegamento :)

	@ManyToOne
	@MapsId("filmId")
	@JoinColumn(name = "film_id")
	private Film film;

	@ManyToOne
	@MapsId("actorId")
	@JoinColumn(name = "actor_id")
	private Actor actor;

	public FilmActor() {
		super();
	}

	public FilmActor(FilmActorPK filmActorPK, Film film, Actor actor) {
		super();
		this.filmActorPK = filmActorPK;
		this.film = film;
		this.actor = actor;
	}

	public FilmActorPK getFilmActorPK() {
		return filmActorPK;
	}

	public void setFilmActorPK(FilmActorPK filmActorPK) {
		this.filmActorPK = filmActorPK;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

}
