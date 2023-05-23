package com.dao;

import java.util.List;

import com.entity.City;
import com.entity.Country;

public interface CountryCityDAO {

	/* Create */
	public void createCityCountry(Country country, List<City> cities);

	public void createCountry(Country country);

	public void createCity(int countryId, City city);

	/* Update */
	public void updateCountry(Country country);

	public void updateCity(City city);

	/* Delete */
	public void deleteCountry(int countryId);

	public void deleteCity(int cityId);

	/* Read */
	public List<Country> readAllCountries();

	public List<City> readAllCities();
}
