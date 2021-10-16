package com.simplilearn.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simplilearn.entities.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	List<Purchase> findByUserId(Long id);
}