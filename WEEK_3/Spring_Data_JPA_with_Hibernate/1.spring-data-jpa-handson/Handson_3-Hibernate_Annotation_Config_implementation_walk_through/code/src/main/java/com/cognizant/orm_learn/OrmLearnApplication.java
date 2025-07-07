package com.cognizant.orm_learn;

import java.util.List;

import com.cognizant.orm_learn.model.Country;
import com.cognizant.orm_learn.service.CountryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
    private static CountryService countryService;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
        countryService = context.getBean(CountryService.class);

        LOGGER.info("Inside main");
        testGetAllCountries();
        testFindByName();
        testFindByNameContaining();
        testFindByNameStartingWith();
        testFindByNameEndingWith();
        testFindAllOrderByNameAsc();
    }

    private static void testGetAllCountries() {
        LOGGER.info("Start - testGetAllCountries");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("Countries: {}", countries);
        LOGGER.info("End - testGetAllCountries");
    }

    private static void testFindByName() {
        LOGGER.info("Start - testFindByName");
        Country country = countryService.findByName("India");
        LOGGER.debug("Country: {}", country);
        LOGGER.info("End - testFindByName");
    }

    private static void testFindByNameContaining() {
        LOGGER.info("Start - testFindByNameContaining");
        List<Country> countries = countryService.findByNameContaining("ou");
        LOGGER.debug("Countries: {}", countries);
        LOGGER.info("End - testFindByNameContaining");
    }

    private static void testFindByNameStartingWith() {
        LOGGER.info("Start - testFindByNameStartingWith");
        List<Country> countries = countryService.findByNameStartingWith("Un");
        LOGGER.debug("Countries: {}", countries);
        LOGGER.info("End - testFindByNameStartingWith");
    }

    private static void testFindByNameEndingWith() {
        LOGGER.info("Start - testFindByNameEndingWith");
        List<Country> countries = countryService.findByNameEndingWith("a");
        LOGGER.debug("Countries: {}", countries);
        LOGGER.info("End - testFindByNameEndingWith");
    }

    private static void testFindAllOrderByNameAsc() {
        LOGGER.info("Start - testFindAllOrderByNameAsc");
        List<Country> countries = countryService.findAllOrderByNameAsc();
        LOGGER.debug("Countries: {}", countries);
        LOGGER.info("End - testFindAllOrderByNameAsc");
    }
}
