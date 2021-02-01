package com.example.handmadeBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {
    private String name;
    private String description;
    private Float price;
    private Long category_id;
    private Long user_id;
}
