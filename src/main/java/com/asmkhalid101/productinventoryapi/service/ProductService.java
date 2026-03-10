package com.asmkhalid101.productinventoryapi.service;


import com.asmkhalid101.productinventoryapi.entity.Product;
import com.asmkhalid101.productinventoryapi.exception.InvalidSkuFormatException;
import com.asmkhalid101.productinventoryapi.exception.ProductNotFoundException;
import com.asmkhalid101.productinventoryapi.exception.SkuAlreadyExistsException;
import com.asmkhalid101.productinventoryapi.repository.ProductRepository;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProductService {
    private final ProductRepository repository;

    private final Logger log = LoggerFactory.getLogger(ProductService.class);


    private static final String SKU_PATTERN = "^SKU-[A-Za-z0-9]{8}$";
    // I still haven't learned Sku fully so i am not implementing it. Sorry.


    // Creating New Product
    public Product createProduct(Product product) {

        validateSkuFormat(product.getSku());
        if(repository.existsBySku(product.getSku())) {
            log.warn(product.getSku() + " already exists");
            throw new SkuAlreadyExistsException(product.getSku());
        }

        log.info("Creating new Product with SKU: " + product.getSku());
        return repository.save(product);
    }





    //Getting All Products

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Getting Product By ID

    public Product getProductById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Update failed. Product not found with ID: {}", id);
                    return new ProductNotFoundException("Product Not found " + id);
                });

    }

    // Update Product with logging was too much

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not found " + id));


        if(!existingProduct.getSku().equals(product.getSku())) {
            throw new InvalidSkuFormatException("SKU Can't BE changed");
        }


        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setDescription(product.getDescription()); // Added update Description
//        existingProduct.setSku(product.getSku()); I can't update SKU


        return repository.save(existingProduct);

    }


    // Deleting Product

    public void deleteProduct(Long id) {
        Product existingProduct = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Deletion failed. Product not found with ID: {}", id);
                    return new ProductNotFoundException("Product Not found " + id);
                });

        log.info("Deleting product with ID: {}", id);
        repository.delete(existingProduct);
    }




    // I got this skew method from AI. I still haven't done that class yet. I think but AI helped me
    private void validateSkuFormat(String sku) {
        if (sku == null || !sku.matches(SKU_PATTERN)) {
            log.warn("Invalid SKU format provided: {}", sku);
            throw new InvalidSkuFormatException("Invalid SKU format. Expected format: SKU-XXXXXXXX");
        }
    }
}
