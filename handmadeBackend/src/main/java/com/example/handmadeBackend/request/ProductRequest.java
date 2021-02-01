package com.example.handmadeBackend.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotNull(message = "Name of product is required.")
    private String name;
    @NotNull(message = "Description of product is required.")
    private String description;
    @NotNull(message = "Price of product is required.")
    @PositiveOrZero(message = "Price can't be negative.")
    private Float price;
    private Long category_id;
    private Long user_id;
}
