package com.simplilearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
