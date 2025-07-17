package com.cognizant.countryservice.controller;

import com.cognizant.countryservice.model.Country;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    @GetMapping("/country")
    public Country getCountryIndia() {
        return new Country("IN", "India");
    }
}
