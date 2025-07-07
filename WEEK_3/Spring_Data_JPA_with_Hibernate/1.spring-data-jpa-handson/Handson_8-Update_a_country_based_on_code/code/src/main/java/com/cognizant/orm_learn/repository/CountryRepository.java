package com.cognizant.orm_learn.repository;

import com.cognizant.orm_learn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, String> {
    Country findByName(String name);

    List<Country> findByNameContaining(String keyword);

    List<Country> findByNameStartingWith(String prefix);

    List<Country> findByNameEndingWith(String suffix);

    List<Country> findAllByOrderByNameAsc();

    @Query(name = "Country.findByNameLike")
    List<Country> findByNameLike(@Param("pattern") String pattern);

    @Query(value = "SELECT * FROM country WHERE co_name LIKE ?", nativeQuery = true)
    List<Country> findByNameNative(String pattern);
}
