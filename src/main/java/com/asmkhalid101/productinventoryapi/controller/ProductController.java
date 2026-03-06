package com.asmkhalid101.productinventoryapi.controller;


import com.asmkhalid101.productinventoryapi.entity.Product;
import com.asmkhalid101.productinventoryapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor

public class ProductController {

    private final ProductService service;

    // Get All

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(long id) {

        return ResponseEntity.ok(service.getAllProducts());
    }


    // Getting By id

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }

    // Creating Employee

    @PostMapping
    public  ResponseEntity<Product> createProduct(@RequestBody Product product){
        return ResponseEntity.ok(service.createProduct(product));
    }



    // Updating by the id

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){

        return ResponseEntity.ok(service.updateProduct(id, product));
    }



    // Deleting By id

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct (@PathVariable long id){
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
