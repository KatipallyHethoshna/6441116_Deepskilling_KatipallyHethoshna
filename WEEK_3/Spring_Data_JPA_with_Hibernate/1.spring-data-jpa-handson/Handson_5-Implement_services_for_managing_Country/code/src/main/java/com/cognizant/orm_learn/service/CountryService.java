package com.cognizant.orm_learn.service;

import java.util.List;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.orm_learn.model.Country;
import com.cognizant.orm_learn.repository.CountryRepository;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Transactional
    public Country findByName(String name) {
        return countryRepository.findByName(name);
    }

    @Transactional
    public List<Country> findByNameContaining(String keyword) {
        return countryRepository.findByNameContaining(keyword);
    }

    @Transactional
    public List<Country> findByNameStartingWith(String prefix) {
        return countryRepository.findByNameStartingWith(prefix);
    }

    @Transactional
    public List<Country> findByNameEndingWith(String suffix) {
        return countryRepository.findByNameEndingWith(suffix);
    }

    @Transactional
    public List<Country> findAllOrderByNameAsc() {
        return countryRepository.findAllByOrderByNameAsc();
    }

    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    @Transactional
    public void updateCountry(String code, String newName) {
        Country country = countryRepository.findById(code).orElse(null);
        if (country != null) {
            country.setName(newName);
            countryRepository.save(country);
        }
    }

    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }

    // 🔽 @Query usage
    @Transactional
    public List<Country> searchCountriesByNamePattern(String keyword) {
        return countryRepository.searchCountriesByNamePattern(keyword);
    }
}
