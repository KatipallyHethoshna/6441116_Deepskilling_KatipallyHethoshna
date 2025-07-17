package com.cognizant.countrylookup.service;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(String code) {
        super("Country not found");
    }
}
