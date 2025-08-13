package com.enset.architecturejee_tp2.web;


import com.enset.architecturejee_tp2.entities.Product;
import com.enset.architecturejee_tp2.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping("/index")
    public String index(Model model) {
        List<Product> products=productRepository.findAll();
        model.addAttribute("products", products);

        return "products";
    }
}
