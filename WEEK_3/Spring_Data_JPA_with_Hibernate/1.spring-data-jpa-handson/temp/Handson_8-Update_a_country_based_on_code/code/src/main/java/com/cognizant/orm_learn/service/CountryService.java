package com.cognizant.orm_learn.service;

import com.cognizant.orm_learn.model.Country;
import com.cognizant.orm_learn.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    // Get all countries
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // Find country by exact name
    public Country getCountryByName(String name) {
        return countryRepository.findByName(name);
    }

    // Find countries containing a substring
    public List<Country> getCountriesByNameContaining(String keyword) {
        return countryRepository.findByNameContaining(keyword);
    }

    // Find countries that start with a prefix
    public List<Country> getCountriesByNameStartingWith(String prefix) {
        return countryRepository.findByNameStartingWith(prefix);
    }

    // Find countries that end with a suffix
    public List<Country> getCountriesByNameEndingWith(String suffix) {
        return countryRepository.findByNameEndingWith(suffix);
    }

    // Get all countries ordered by name
    public List<Country> getAllCountriesOrderedByNameAsc() {
        return countryRepository.findAllByOrderByNameAsc();
    }

    // Add a new country
    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    // Update an existing country
    public Country updateCountry(String code, String newName) {
        Optional<Country> optionalCountry = countryRepository.findById(code);
        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();
            country.setName(newName);
            return countryRepository.save(country);
        }
        return null;
    }

    // Delete a country by code
    public void deleteCountry(String code) {
        if (countryRepository.existsById(code)) {
            countryRepository.deleteById(code);
        }
    }

    // Named JPQL query: LIKE pattern
    public List<Country> getCountriesByNamePattern(String pattern) {
        return countryRepository.findByNameLike(pattern);
    }

    // Native SQL query: LIKE pattern
    public List<Country> getCountriesByNativeQuery(String pattern) {
        return countryRepository.findByNameNative(pattern);
    }
}
