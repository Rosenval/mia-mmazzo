package com.dao;

import java.util.List;

import com.entity.Actor;
import com.entity.Film;
import com.entity.FilmActor;
import com.entity.FilmActorPK;
import com.provider.ProviderManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.RollbackException;
import jakarta.persistence.TypedQuery;

public class FilmActorDAOImpl implements FilmActorDAO {

	private EntityManagerFactory emf;
	private EntityManager em;

	@Override
	public List<Film> readAllFilmsByActor(Integer actorId) {
		initRoutine();
		String query = "SELECT fa.film FROM FilmActor fa WHERE fa.actor = :actor";
		Actor actor = em.find(Actor.class, actorId);
		TypedQuery<Film> typedQuery = em.createQuery(query, Film.class).setParameter("actor", actor);
		List<Film> result = typedQuery.getResultList();
		closeRoutine();
		return result;
	}

	@Override
	public List<Actor> readAllActorsByFilm(Integer filmId) {
		initRoutine();
		String query = "SELECT fa.actor FROM FilmActor fa WHERE fa.film = :film";
		Film film = em.find(Film.class, filmId);
		TypedQuery<Actor> typedQuery = em.createQuery(query, Actor.class).setParameter("film", film);
		List<Actor> result = typedQuery.getResultList();
		closeRoutine();
		return result;
	}

	@Override
	public void createFilm(Film film) {
		initRoutine();
		em.persist(film);
		closeRoutine();
	}

	@Override
	public void createActor(Actor actor) {
		initRoutine();
		em.persist(actor);
		closeRoutine();
	}

	@Override
	public void createFilmWithActors(Film film, List<Actor> actors) {
		initRoutine();
		em.persist(film);
		for (Actor a : actors) {
			em.persist(a);
			FilmActorPK filmActorPK = new FilmActorPK(a.getActorId(), film.getFilmId());
			FilmActor filmActor = new FilmActor(filmActorPK, film, a);
			em.persist(filmActor);
		}
		closeRoutine();
	}

	@Override
	public void createActorWithFilms(Actor actor, List<Film> films) {
		initRoutine();
		em.persist(actor);
		for (Film f : films) {
			em.persist(f);
			FilmActorPK filmActorPK = new FilmActorPK(actor.getActorId(), f.getFilmId());
			FilmActor filmActor = new FilmActor(filmActorPK, f, actor);
			em.persist(filmActor);
		}
		closeRoutine();
	}

	@Override
	public void createActorsInFilm(int filmId, List<Actor> actors) {
		initRoutine();
		Film findFilm = em.find(Film.class, filmId);
		for (Actor a : actors) {
			em.persist(a);
			FilmActorPK filmActorPK = new FilmActorPK(a.getActorId(), findFilm.getFilmId());
			FilmActor filmActor = new FilmActor(filmActorPK, findFilm, a);
			em.persist(filmActor);
		}
		closeRoutine();
	}

	@Override
	public void createFilmsInActor(int actorId, List<Film> films) {
		initRoutine();
		Actor findActor = em.find(Actor.class, actorId);
		for (Film f : films) {
			em.persist(f);
			FilmActorPK filmActorPK = new FilmActorPK(findActor.getActorId(), f.getFilmId());
			FilmActor filmActor = new FilmActor(filmActorPK, f, findActor);
			em.persist(filmActor);
		}
		closeRoutine();
	}

	@Override
	public void updateFilm(Film film) {
		initRoutine();
		em.merge(film);
		closeRoutine();
	}

	@Override
	public void updateActor(Actor actor) {
		initRoutine();
		em.merge(actor);
		closeRoutine();
	}

	@Override
	public void deleteFilm(int filmId) {
		initRoutine();
		Film film = em.find(Film.class, filmId);
		String query = "SELECT fa FROM FilmActor fa WHERE fa.filmActorPK.filmId = :filmId";
		TypedQuery<FilmActor> typedQuery = em.createQuery(query, FilmActor.class).setParameter("filmId", filmId);
		List<FilmActor> filmActor = typedQuery.getResultList();
		for (FilmActor fa : filmActor) {
			em.remove(fa);
		}
		em.remove(film);
		// non mi fa eliminare il film pd
		// Unknown column 'films_film_id' in 'where clause'
		// eppure quello dell'Actor lo trova
		// e sono stati creati identici wtf
		closeRoutine();
	}

	@Override
	public void deleteActor(int actorId) {
		initRoutine();
		Actor actor = em.find(Actor.class, actorId);
		String query = "SELECT fa FROM FilmActor fa WHERE fa.filmActorPK.actorId = :actorId";
		TypedQuery<FilmActor> typedQuery = em.createQuery(query, FilmActor.class).setParameter("actorId", actorId);
		List<FilmActor> filmActor = typedQuery.getResultList();
		for (FilmActor fa : filmActor) {
			em.remove(fa);
		}
		em.remove(actor);
		closeRoutine();
	}

	private void initRoutine() {
		emf = ProviderManager.getEntityManagerFactory();
		em = ProviderManager.getEntityManager(emf);
		ProviderManager.beginTransaction(em);
	}

	private void closeRoutine() {
		try {
			ProviderManager.commitTransaction(em);
		} catch (RollbackException rbe) {
			System.err.println("Transazione fallita: eseguo il rollback.");
			rbe.printStackTrace();
			ProviderManager.rollbackTransaction(em);
		} finally {
			ProviderManager.closeTransaction(em);
			ProviderManager.closeEntityManagerFactory(emf);
		}
	}
}
