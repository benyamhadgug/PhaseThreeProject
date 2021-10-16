package com.simplilearn.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.entities.User;
import com.simplilearn.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void save(User user) {
		this.userRepository.save(user);
	}

	public Optional<User> findById(long id) {
		return this.userRepository.findById(id);
		
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return this.userRepository.findAll();
	}

	public void delete(User user) {
		this.userRepository.delete(user);
	}
}
