package com.simplilearn.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
