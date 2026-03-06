package com.asmkhalid101.productinventoryapi.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Names can't be empty")
    private String name;


    @Size(max = 500, message = "Description Should be under 500 characters")
    private String description;

    @NotEmpty
    private String sku;


    @NotNull
    @Positive(message = "Price Should be Positive")
    private Double price;


    @NotNull(message = "Quantity must not be null")
    @Min(value = 0, message = "Quantity must not be less than 0")
    private Integer quantity;


    @NotNull(message = "Status Can't be null")
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;
}
