package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.Cities;
import com.app.entites.States;
@Repository
public interface CitiesRepository extends JpaRepository<Cities, Integer> {

      List<Cities> findByStateId(States states);

	

}
