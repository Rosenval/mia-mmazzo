package com.dao;

import java.util.List;

import com.entity.City;
import com.entity.Country;
import com.provider.ProviderManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.RollbackException;

public class CountryCityDAOImpl implements CountryCityDAO {

	private EntityManagerFactory emf;
	private EntityManager em;

	/////// NON STO IN CASCADE ///////

	@Override
	public void createCityCountry(Country country, List<City> cities) {
		initRoutine();
		// 1. faccio prima il persist del Country creato
		// 2. per ogni City nella lista creata, setto il Country appena persistito
		// 3. faccio il persist di ogni City
		em.persist(country);
		for (City c : cities) {
			c.setCountry(country);
			em.persist(c);
		}
		closeRoutine();
	}

	@Override
	public void createCountry(Country country) {
		initRoutine();
		em.persist(country);
		closeRoutine();
	}

	@Override
	public void createCity(int countryId, City city) {
		initRoutine();
		// crea City appartenente ad un Country gia esistente
		// 1. trovo il country dal suo I
		// 2. lo setto alla City appena creata
		// 3. aggiungo la City alla lista di City del Country
		// 4. persisto la city
		Country country = em.find(Country.class, countryId);
		city.setCountry(country);
		country.getCities().add(city);
		em.persist(city);
		closeRoutine();
	}

	@Override
	public void updateCountry(Country country) {
		initRoutine();
		em.merge(country);
		closeRoutine();
	}

	@Override
	public void updateCity(City city) {
		initRoutine();
		// 1. recupero l'entità della City da aggiornare dal database in base all'id
		// (tutto cio' lo assegno alla variabile cityToMerge di tipo City)

		// 2. aggiorno il nome della City nell'entità cityToMerge con il nome della
		// città fornito. cosi' il nome della città viene modificato all'interno
		// dell'entità

		// 3. recupero l'entita' Country aggiornata dal database in base
		// al countryId fornito (tutto cio' lo assegno alla variabile updatedCountry di
		// tipo Country)

		// 4. aggiungo l'entita' cityToMerge (cioè la citta' che è stata
		// aggiornata) alla collezione di citta' nell'entita' updatedCountry:
		// la collezione di città viene aggiornata con la nuova città

		// 5. imposto updatedCountry come paese per cityToMerge
		// cosi' viene aggiornata la relazione tra la City e il
		// Country in City

		// 6. faccio il merge: unisco le modifiche a cityToMerge con l'entita'
		// corrispondente nel database. il merge aggiorna l'entita'
		// della citta' esistente nel database con i valori modificati di
		// cityToMerge :)

		City cityToMerge = em.find(City.class, city.getCityId());
		cityToMerge.setCity(city.getCity());

//		Country countryOfCTM = cityToMerge.getCountry();
//		countryOfCTM.getCities().remove(cityToMerge);
		Country updatedCountry = em.find(Country.class, city.getCountry().getCountryId());
		updatedCountry.getCities().add(cityToMerge);
		cityToMerge.setCountry(updatedCountry);
		em.merge(cityToMerge);
		closeRoutine();
	}

	@Override
	public void deleteCountry(int countryId) {
		initRoutine();
		// senza Cascade quindi
		// 1. ho prima rimosso a mano tutte le City di quel determinato Country
		// 2. ho rimosso il Country
		Country countryToDelete = em.find(Country.class, countryId);
		for (City c : countryToDelete.getCities()) {
			em.remove(c);
		}
		em.remove(countryToDelete);
		closeRoutine();
	}

	@Override
	public void deleteCity(int cityId) {
		initRoutine();
		City cityToDelete = em.find(City.class, cityId);
		em.remove(cityToDelete);
		closeRoutine();
	}

	@Override
	public List<Country> readAllCountries() {
		initRoutine();
		List<Country> countries = em.createNamedQuery("Country.findAll", Country.class).getResultList();
		closeRoutine();
		return countries;
	}

	@Override
	public List<City> readAllCities() {
		initRoutine();
		List<City> cities = em.createNamedQuery("City.findAll", City.class).getResultList();
		closeRoutine();
		return cities;
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
