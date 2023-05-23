package com.main;

import java.util.ArrayList;
import java.util.List;

import com.dao.FilmActorDAO;
import com.dao.FilmActorDAOImpl;
import com.entity.Actor;
import com.entity.Film;

public class Main {

	public static void main(String[] args) {

		FilmActorDAO fad = new FilmActorDAOImpl();
		/* Read */
//		fad.readAllFilmsByActor(1).forEach(System.out::println);
//		fad.readAllActorsByFilm(1).forEach(System.out::println);

		/* Create */
		// singoli
//		Film film = new Film(1, 7, 0.99, 14.99, "Fabulous film");
//		fad.createFilm(film);
//		Actor actor = new Actor("Mia", "Queen");
//		fad.createActor(actor);

		// creo Film con Actors
		Film film1 = new Film(1, 7, 0.99, 14.99, "new Film with Actors");
		List<Actor> actors = new ArrayList<>();
		Actor actor1 = new Actor("One", "New");
		Actor actor2 = new Actor("Two", "New");
		Actor actor3 = new Actor("Three", "New");
		actors.add(actor1);
		actors.add(actor2);
		actors.add(actor3);

		fad.createFilmWithActors(film1, actors);

		// creo Actor con Films
		Actor actor4 = new Actor("Mimi", "R");
		List<Film> films = new ArrayList<>();
		Film film2 = new Film(1, 7, 0.99, 14.99, "Manco volevo nasce");
		Film film3 = new Film(1, 7, 0.99, 14.99, "Figuriamoci fare l'attrice");
		Film film4 = new Film(1, 7, 0.99, 14.99, "Le disavventure di Mia con Java");
		films.add(film2);
		films.add(film3);
		films.add(film4);

		fad.createActorWithFilms(actor4, films);

		// creo Actors in Film
		List<Actor> actors1 = new ArrayList<>();
		Actor actor5 = new Actor("Four", "New");
		Actor actor6 = new Actor("Five", "New");
		Actor actor7 = new Actor("Six", "New");
		actors1.add(actor5);
		actors1.add(actor6);
		actors1.add(actor7);

		fad.createActorsInFilm(1002, actors1);

		// creo Films in Actor
		List<Film> films1 = new ArrayList<>();
		Film film5 = new Film(1, 7, 0.99, 14.99, "Ho");
		Film film6 = new Film(1, 7, 0.99, 14.99, "Perso");
		Film film7 = new Film(1, 7, 0.99, 14.99, "L'inventiva");
		films1.add(film5);
		films1.add(film6);
		films1.add(film7);

		fad.createFilmsInActor(204, films1);

		// Update
		actor7.setFirstName("Seven");
		fad.updateActor(actor7);
//		film4.setTitle("Sta andando tutto maleee");
//		fad.updateFilm(film4);

		// prova delete
		fad.deleteFilm(1006);
		fad.deleteActor(204);
	}
}
