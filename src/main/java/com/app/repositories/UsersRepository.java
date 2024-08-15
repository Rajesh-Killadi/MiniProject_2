package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entites.UsersMaster;
@Repository
public interface UsersRepository extends JpaRepository<UsersMaster, Integer> {

	UsersMaster findByEmailAndPassword(String email, String password);

	UsersMaster findByEmail(String email);

}
