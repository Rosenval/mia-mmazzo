package com.main;

import java.util.ArrayList;
import java.util.List;

import com.dao.CountryCityDAO;
import com.dao.CountryCityDAOImpl;
import com.entity.City;
import com.entity.Country;

public class Main {

	public static void main(String[] args) {

		CountryCityDAO ccd = new CountryCityDAOImpl();
		// Read di tutti i countries
		ccd.readAllCountries().forEach(System.out::println);

		// Read di tutte le city con annessi i suoi countries
		ccd.readAllCities().forEach(System.out::println);

		// Create country + city
		Country country = new Country("Fabulous Country");
		Country country2 = new Country("Country Cavia"); // id: 112
		ccd.createCountry(country2);
		Country countryToDelete = new Country("No Name tanto non lo vedrai nel DB :)");

		City city1 = new City("F city");
		City city2 = new City("A city"); // id: 602
		City city3 = new City("B city");

		City city4 = new City("N city");
		City city5 = new City("O city");

		List<City> cities = new ArrayList<>();
		cities.add(city1);
		cities.add(city2);
		cities.add(city3);

		List<City> ghostCities = new ArrayList<>();
		ghostCities.add(city4);
		ghostCities.add(city5);

		ccd.createCityCountry(country, cities);
		ccd.createCityCountry(countryToDelete, ghostCities);

		// Create City appartenente a Country esistente
		City city6 = new City("Fabulous new city");
		ccd.createCity(108, city6);

		// Update Country
		// ripersisto country per rimetterlo nel persistence context
		// usando il costruttore con l'id per ricavarne l'id + il nome per aggiornare
		// con il nome nuovo
		country.setCountry("Country modified but Fabulous");
		ccd.updateCountry(country);

		// Update City
		// cambio il nome
		city6.setCity("City modified but fabulous");
		ccd.updateCity(city6);
		// cambio il paese a cui appartiene
		city6.setCountry(country2);
		ccd.updateCity(city6);

		// Delete Country
		ccd.deleteCountry(countryToDelete.getCountryId());
		// Delete City
		ccd.deleteCity(city2.getCityId());
	}

}
