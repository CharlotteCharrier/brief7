package fr.simplon.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.simplon.api.models.Product;
import fr.simplon.api.repositories.ProductRepository;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    //récupérer tous les produits
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //récupérer un produit par son id
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Integer id) {
        return productRepository.findById(id);
    }

    //créer un nouveau produit
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    //supprimer un produit
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Integer id) {
        productRepository.deleteById(id);
    }

}
