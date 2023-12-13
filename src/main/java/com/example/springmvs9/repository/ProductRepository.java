package com.example.springmvs9.repository;

import com.example.springmvs9.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
