package com.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.store.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{
	List<Product> findByCategoryId(String cid);
}
