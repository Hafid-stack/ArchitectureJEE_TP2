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
    @GetMapping("/user/index")
    public String index(Model model) {
        List<Product> products=productRepository.findAll();
        model.addAttribute("products", products);

        return "products";
    }

    @GetMapping("/")
    public String home() {

        return "redirect:/user/index";
    }


    @GetMapping("/admin/delete")
    public String delete(@RequestParam(name="id") Long id) {
        productRepository.deleteById( id);
        return "redirect:/user/index";
    }
    @GetMapping("/admin/newProduct")
    public String newProduct(Model model) {
        model.addAttribute("product", new Product());
        return "new-product";

    }
    @PostMapping("/admin/saveProduct")
    public String saveProduct(@Valid Product product, BindingResult bindingResult,Model model) {
        if (bindingResult.hasErrors()) {
            return "new-product";
        }
        productRepository.save(product);
        return "redirect:/user/index";
    }
    @PostMapping("/admin/editProduct")
    public String editProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-product"; // Return to the same edit page on error
        }

        // Spring will automatically create and populate a new Product object from the form data,
        // including the hidden ID field.
        // The save method will update the existing product because the ID is present.
        productRepository.save(product);

        return "redirect:/user/index";
    }
    @GetMapping("/admin/editProduct")
    public String editProduct(@RequestParam("id") Long id, Model model) {
        // Fetch the product from the database
        Product product = productRepository.findById(id).orElse(null);

        // Add the product to the model
        model.addAttribute("product", product);

        // Return the name of the template
        return "edit-product";
    }
}
