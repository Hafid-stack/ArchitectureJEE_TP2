package com.enset.architecturejee_tp2.repository;

import com.enset.architecturejee_tp2.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {



}
