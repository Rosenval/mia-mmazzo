package com.dao;

import java.util.List;

import com.entity.Actor;
import com.entity.Film;

public interface FilmActorDAO {
	/* Read */
	public List<Film> readAllFilmsByActor(Integer actorId);

	public List<Actor> readAllActorsByFilm(Integer filmId);

	/* Insert */
	public void createFilm(Film film);

	public void createActor(Actor actor);

	public void createFilmWithActors(Film film, List<Actor> actors);

	public void createActorWithFilms(Actor actor, List<Film> films);

	public void createFilmsInActor(int actorId, List<Film> films);

	public void createActorsInFilm(int filmId, List<Actor> actors);

	/* Update */
	public void updateFilm(Film film);

	public void updateActor(Actor actor);

	/* Delete */
	public void deleteFilm(int filmId);

	public void deleteActor(int actorId);

}
