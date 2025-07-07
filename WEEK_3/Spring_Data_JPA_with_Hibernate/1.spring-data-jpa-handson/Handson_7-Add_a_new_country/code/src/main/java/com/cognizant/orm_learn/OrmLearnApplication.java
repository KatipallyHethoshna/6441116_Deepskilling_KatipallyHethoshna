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
        testAddCountry();
        testUpdateCountry();
        testDeleteCountry();
        testSearchCountriesByNamePattern();
        testFindByNameLike();
        testFindCountriesByNativeQuery();
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

    // 🔽 Added for Hands-on 4

    private static void testAddCountry() {
        LOGGER.info("Start - testAddCountry");
        Country newCountry = new Country();
        newCountry.setCode("ZZ");
        newCountry.setName("Zootopia");
        countryService.addCountry(newCountry);
        LOGGER.debug("Added Country: {}", countryService.findByName("Zootopia"));
        LOGGER.info("End - testAddCountry");
    }

    private static void testUpdateCountry() {
        LOGGER.info("Start - testUpdateCountry");
        countryService.updateCountry("ZZ", "Updated Zootopia");
        LOGGER.debug("Updated Country: {}", countryService.findByName("Updated Zootopia"));
        LOGGER.info("End - testUpdateCountry");
    }

    private static void testDeleteCountry() {
        LOGGER.info("Start - testDeleteCountry");
        countryService.deleteCountry("ZZ");
        Country deleted = countryService.findByName("Updated Zootopia");
        LOGGER.debug("Country after deletion (should be null): {}", deleted);
        LOGGER.info("End - testDeleteCountry");
    }
    private static void testSearchCountriesByNamePattern() {
        LOGGER.info("Start - testSearchCountriesByNamePattern");
        List<Country> countries = countryService.searchCountriesByNamePattern("a");
        LOGGER.debug("Countries matching pattern 'a': {}", countries);
        LOGGER.info("End - testSearchCountriesByNamePattern");
    }
    private static void testFindByNameLike() {
        LOGGER.info("Start - testFindByNameLike");
        List<Country> countries = countryService.findByNameLike("a");
        LOGGER.debug("Countries matching pattern with named query: {}", countries);
        LOGGER.info("End - testFindByNameLike");
    }
    private static void testFindCountriesByNativeQuery() {
        LOGGER.info("Start - testFindCountriesByNativeQuery");
        List<Country> countries = countryService.findCountriesByNativeQuery("a");
        LOGGER.debug("Countries matching native query: {}", countries);
        LOGGER.info("End - testFindCountriesByNativeQuery");
    }


}
