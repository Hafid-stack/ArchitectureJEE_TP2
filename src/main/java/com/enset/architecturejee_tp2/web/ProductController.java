package com.enset.architecturejee_tp2.web;


import com.enset.architecturejee_tp2.entities.Product;
import com.enset.architecturejee_tp2.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/")
    public String index() {

        return "redirect:/index";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam(name="id") Long id) {
        productRepository.deleteById( id);
        return "redirect:/index";
    }
    @GetMapping("/newProduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "new-product";

    }
    @PostMapping("/saveProduct")
    public String saveProduct(@Valid Product product, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "new-product";
        }
        productRepository.save(product);
        return "redirect:/index";
    }
}
