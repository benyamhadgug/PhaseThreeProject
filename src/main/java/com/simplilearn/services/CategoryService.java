package com.simplilearn.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplilearn.entities.Category;
import com.simplilearn.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void save(Category Category) {
		this.categoryRepository.save(Category);
	}

	public Optional<Category> findById(long id) {
		return this.categoryRepository.findById(id);
		
	}

	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return this.categoryRepository.findAll();
	}

	public void delete(Category Category) {
		this.categoryRepository.delete(Category);
	}
}
