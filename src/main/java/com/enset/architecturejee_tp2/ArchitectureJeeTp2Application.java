package com.enset.architecturejee_tp2;

import com.enset.architecturejee_tp2.entities.Product;
import com.enset.architecturejee_tp2.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ArchitectureJeeTp2Application {

    public static void main(String[] args) {
        SpringApplication.run(ArchitectureJeeTp2Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ProductRepository productRepository ) {
        return args -> {
            productRepository.save(Product.builder()
                    .name("Computer")
                            .price(10000)
                                    .quantity(10)
                    .build());
            productRepository.save(Product.builder()
                    .name("Laptop")
                    .price(15000)
                    .quantity(150)
                    .build());
            productRepository.save(Product.builder()
                    .name("Iphone")
                    .price(99999)
                    .quantity(99)
                    .build());
            productRepository.findAll().forEach(
                    product -> {
                        System.out.println(product.toString());
                    }
            );
        };
    }

}
