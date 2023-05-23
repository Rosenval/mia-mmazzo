package com.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

// questa classe rappresenta la chiave primaria composta per l'entita' FilmActor

// avere questa chiave primaria ci permette di identificare univocamente
// le associazioni tra attori e film nella tabella di collegamento

// annotazione che indica che le istanze di questa classe possono essere
// incorporate all'interno di altre entità come chiave primaria composta
@Embeddable
public class FilmActorPK implements Serializable {

	private static final long serialVersionUID = 6020018290096896992L;

	// actorId e filmId corrispondono alle colonne primary key nella tabella di
	// collegamento che unisce le tabelle Film e Actor
	// ed infatti @Column viene utilizzata per specificare la mappatura tra i campi
	// e le rispettive colonne del database
	// in questo caso actorId è mappato sulla colonna "actor_id"
	// mentre il campo filmId è mappato sulla colonna "film_id".

	@Column(name = "actor_id")
	private Integer actorId;

	@Column(name = "film_id")
	private Integer filmId;

	public FilmActorPK() {
		super();
	}

	public FilmActorPK(Integer actorId, Integer filmId) {
		super();
		this.actorId = actorId;
		this.filmId = filmId;
	}

	public Integer getActorId() {
		return actorId;
	}

	public void setActorId(Integer actorId) {
		this.actorId = actorId;
	}

	public Integer getFilmId() {
		return filmId;
	}

	public void setFilmId(Integer filmId) {
		this.filmId = filmId;
	}

}
