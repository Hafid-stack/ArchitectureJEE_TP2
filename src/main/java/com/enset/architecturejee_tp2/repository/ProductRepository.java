package com.enset.architecturejee_tp2.repository;

import com.enset.architecturejee_tp2.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


//    List<Product> id(long id);
}
