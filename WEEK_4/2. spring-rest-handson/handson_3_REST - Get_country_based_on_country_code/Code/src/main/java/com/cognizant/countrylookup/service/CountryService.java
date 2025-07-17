package com.cognizant.countrylookup.service;

import com.cognizant.countrylookup.model.Country;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CountryService {

    private static final List<Country> countries = Arrays.asList(
        new Country("IN", "India"),
        new Country("US", "United States"),
        new Country("JP", "Japan"),
        new Country("DE", "Germany")
    );

    public Country getCountry(String code) {
        return countries.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new CountryNotFoundException(code));
    }
}
