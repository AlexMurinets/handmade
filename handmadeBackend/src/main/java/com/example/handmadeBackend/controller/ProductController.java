package com.example.handmadeBackend.controller;

import com.example.handmadeBackend.dto.ProductDto;
import com.example.handmadeBackend.model.Product;
import com.example.handmadeBackend.request.ProductRequest;
import com.example.handmadeBackend.response.ProductResponse;
import com.example.handmadeBackend.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ProductDto productDto = modelMapper.map(productRequest, ProductDto.class);
        ProductDto createdProduct = productService.createProduct(productDto);
        ProductResponse productResponse = modelMapper.map(createdProduct, ProductResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(productResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Product> showProducts(@PathVariable Long id){
//        if (productService.existProduct(id))
            return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
