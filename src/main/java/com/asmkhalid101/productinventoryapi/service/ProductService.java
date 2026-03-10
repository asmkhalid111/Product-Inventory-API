package com.asmkhalid101.productinventoryapi.service;


import com.asmkhalid101.productinventoryapi.entity.Product;
import com.asmkhalid101.productinventoryapi.exception.ProductNotFoundException;
import com.asmkhalid101.productinventoryapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProductService {
    private final ProductRepository repository;


    private static final String SKU_PATTERN = "^SKU-[A-Za-z0-9]{8}$";


    // Creating New Product
    public Product createProduct(Product product) {
        return repository.save(product);
    }


    //Getting All Products

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // Getting Product By ID

    public Product getProductById(long id){
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not found " + id));
    }

    // Update Product

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not found " + id));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setSku(product.getSku());
        return repository.save(existingProduct);

    }


    // Deleting Product

    public void deleteProduct(Long id) {
        Product existingProduct = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not found " + id));

        repository.delete(existingProduct);
    }
}
