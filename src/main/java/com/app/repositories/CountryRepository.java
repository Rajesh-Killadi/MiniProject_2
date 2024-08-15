package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.Countries;
@Repository
public interface CountryRepository extends JpaRepository<Countries , Integer> {

}
