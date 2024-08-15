package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.Countries;
import com.app.entites.States;
@Repository
public interface StatesRepository extends JpaRepository<States,Integer> {

	List<States> findByCountryId(Countries countryId);

	

}
