package com.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
}
