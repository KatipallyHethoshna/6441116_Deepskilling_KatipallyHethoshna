package com.cognizant.countrydemo.spring_country_demo;

import com.cognizant.countrydemo.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringCountryDemoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringCountryDemoApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START - main()");

        SpringApplication.run(SpringCountryDemoApplication.class, args);

        displayCountry();

        LOGGER.info("END - main()");
    }

    public static void displayCountry() {
        LOGGER.info("START - displayCountry()");

        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml")) {
            Country country = context.getBean("country", Country.class);
            LOGGER.debug("Country: {}", country.toString());
        }

        LOGGER.info("END - displayCountry()");
    }

}
