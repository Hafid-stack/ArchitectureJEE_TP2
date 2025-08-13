package com.enset.architecturejee_tp2;

import com.enset.architecturejee_tp2.entities.Product;
import com.enset.architecturejee_tp2.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ArchitectureJeeTp2Application {

    public static void main(String[] args) {
        SpringApplication.run(ArchitectureJeeTp2Application.class, args);
    }
//Update the lomboc because for some reason it wont work
//Deactivate Spring Security
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
