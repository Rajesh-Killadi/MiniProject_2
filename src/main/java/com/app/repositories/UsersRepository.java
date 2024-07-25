package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entites.UsersMaster;

public interface UsersRepository extends JpaRepository<UsersMaster, Integer> {

	UsersMaster findByEmailAndPassword(String email, String password);

	UsersMaster findByEmail(String email);

}
