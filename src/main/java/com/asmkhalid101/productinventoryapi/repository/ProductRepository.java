package com.asmkhalid101.productinventoryapi.repository;

import com.asmkhalid101.productinventoryapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsBySku(String sku);


}
