package com.cognizant.orm_learn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.orm_learn.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    Country findByName(String name);

    List<Country> findByNameContaining(String keyword);

    List<Country> findByNameStartingWith(String prefix);

    List<Country> findByNameEndingWith(String suffix);

    List<Country> findAllByOrderByNameAsc();
}
