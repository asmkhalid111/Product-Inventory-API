package com.asmkhalid101.productinventoryapi.controller;


import com.asmkhalid101.productinventoryapi.entity.Product;
import com.asmkhalid101.productinventoryapi.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor

public class ProductController {

    // Added valid annotation that i forgot to add.

    private final ProductService service;

    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    // Get All

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        log.debug("Received request to fetch all products");
        List<Product> products = service.getAllProducts();
        log.info("Fetched {} products", products.size());
        return ResponseEntity.ok(products);
    }

    // Getting By id

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        log.debug("Received request to fetch product with ID: {}", id);
        Product product = service.getProductById(id);
        log.info("Fetched product with ID: {}", id);
        return ResponseEntity.ok(product);
    }

    // Creating Product

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        log.debug("Received request to create product: {}", product);
        Product savedProduct = service.createProduct(product);
        log.info("Product created with ID: {} and SKU: {}", savedProduct.getId(), savedProduct.getSku());
        return ResponseEntity.ok(savedProduct);
    }



    // Updating by the id

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product){
        log.debug("Received request to update product with ID: {}", id);
        Product updatedProduct = service.updateProduct(id, product);
        log.info("Product updated with ID: {} and SKU: {}", updatedProduct.getId(), updatedProduct.getSku());
        return ResponseEntity.ok(updatedProduct);
    }



    // Deleting By id

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct (@PathVariable long id){
        log.debug("Received request to delete product with ID: {}", id);
        service.deleteProduct(id);
        log.info("Product deleted with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

}
