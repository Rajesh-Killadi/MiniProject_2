package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entites.User;

public interface UsersRepository extends JpaRepository<User, Integer> {

	User findByEmailAndPassword(String email, String password);

	User findByEmail(String email);

}
