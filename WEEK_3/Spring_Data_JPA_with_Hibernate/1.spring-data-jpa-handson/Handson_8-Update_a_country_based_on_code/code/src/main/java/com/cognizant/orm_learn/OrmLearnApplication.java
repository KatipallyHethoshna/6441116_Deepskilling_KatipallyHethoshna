package com.cognizant.orm_learn;

import com.cognizant.orm_learn.model.Country;
import com.cognizant.orm_learn.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class OrmLearnApplication {

    @Autowired
    private CountryRepository countryRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrmLearnApplication.class, args);
    }

    @PostConstruct
    public void runApp() {
        System.out.println("Inside main");

        testGetAllCountries();
        testFindByName();
        testFindByNameContaining();
        testFindByNameStartingWith();
        testFindByNameEndingWith();
        testFindAllOrderByNameAsc();
        testAddCountry();
        testUpdateCountry();
        testDeleteCountry();
        testSearchCountriesByNamePattern();
        testFindByNameLike();
        testFindCountriesByNativeQuery();
    }

    public void testGetAllCountries() {
        System.out.println("Start - testGetAllCountries");
        List<Country> countries = countryRepository.findAll();
        System.out.println("Countries = " + countries);
        System.out.println("End - testGetAllCountries");
    }

    public void testFindByName() {
        System.out.println("Start - testFindByName");
        Country country = countryRepository.findByName("India");
        System.out.println("Country: " + country);
        System.out.println("End - testFindByName");
    }

    public void testFindByNameContaining() {
        System.out.println("Start - testFindByNameContaining");
        List<Country> countries = countryRepository.findByNameContaining("a");
        System.out.println("Countries: " + countries);
        System.out.println("End - testFindByNameContaining");
    }

    public void testFindByNameStartingWith() {
        System.out.println("Start - testFindByNameStartingWith");
        List<Country> countries = countryRepository.findByNameStartingWith("U");
        System.out.println("Countries: " + countries);
        System.out.println("End - testFindByNameStartingWith");
    }

    public void testFindByNameEndingWith() {
        System.out.println("Start - testFindByNameEndingWith");
        List<Country> countries = countryRepository.findByNameEndingWith("a");
        System.out.println("Countries: " + countries);
        System.out.println("End - testFindByNameEndingWith");
    }

    public void testFindAllOrderByNameAsc() {
        System.out.println("Start - testFindAllOrderByNameAsc");
        List<Country> countries = countryRepository.findAllByOrderByNameAsc();
        System.out.println("Countries: " + countries);
        System.out.println("End - testFindAllOrderByNameAsc");
    }

    public void testAddCountry() {
        System.out.println("Start - testAddCountry");
        if (!countryRepository.existsById("JP")) {
            Country country = new Country("JP", "Japan");
            countryRepository.save(country);
        }
        Country added = countryRepository.findByName("Japan");
        System.out.println("Added Country: " + added);
        System.out.println("End - testAddCountry");
    }

    public void testUpdateCountry() {
        System.out.println("Start - testUpdateCountry");
        Country country = countryRepository.findById("JP").orElse(null);
        if (country != null) {
            country.setName("Japan Updated");
            countryRepository.save(country);
        }
        Country updated = countryRepository.findByName("Japan Updated");
        System.out.println("Updated Country: " + updated);
        System.out.println("End - testUpdateCountry");
    }

    public void testDeleteCountry() {
        System.out.println("Start - testDeleteCountry");
        if (countryRepository.existsById("JP")) {
            countryRepository.deleteById("JP");
            System.out.println("Deleted country with code JP");
        }
        System.out.println("End - testDeleteCountry");
    }

    public void testSearchCountriesByNamePattern() {
        System.out.println("Start - testSearchCountriesByNamePattern");
        List<Country> countries = countryRepository.findByNameContaining("a");
        System.out.println("Countries matching pattern: " + countries);
        System.out.println("End - testSearchCountriesByNamePattern");
    }

    public void testFindByNameLike() {
        System.out.println("Start - testFindByNameLike");
        List<Country> countries = countryRepository.findByNameLike("%a%");
        System.out.println("Countries (Named Query): " + countries);
        System.out.println("End - testFindByNameLike");
    }

    public void testFindCountriesByNativeQuery() {
        System.out.println("Start - testFindCountriesByNativeQuery");
        List<Country> countries = countryRepository.findByNameNative("%a%");
        System.out.println("Countries (Native Query): " + countries);
        System.out.println("End - testFindCountriesByNativeQuery");
    }
}
