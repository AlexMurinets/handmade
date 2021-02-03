package com.example.handmadeBackend.service;

import com.example.handmadeBackend.dto.ProductDto;
import com.example.handmadeBackend.model.Product;
import com.example.handmadeBackend.repository.CategoryRepository;
import com.example.handmadeBackend.repository.ProductRepository;
import com.example.handmadeBackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ProductDto createProduct(ProductDto productDto){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Product product = modelMapper.map(productDto, Product.class);
        product.setCategory(categoryRepository.findById(productDto.getCategory_id()).orElseThrow(()->new NullPointerException("Category doesn't exist.")));
        product.setUser(userRepository.findById(productDto.getUser_id()).orElseThrow(()->new NullPointerException("User doesn't exist.")));
        productRepository.save(product);
        ProductDto returnProduct = modelMapper.map(product, ProductDto.class);
        returnProduct.setCategory_id(product.getCategory().getId());
        returnProduct.setUser_id(product.getUser().getId());
        return returnProduct;
    }

    public Boolean existProduct(Long id){
        if (productRepository.findById(id).isPresent()){
            return true;
        }
        return false;
    }

    public Product findById(Long id){
        return productRepository.findById(id).orElseThrow(()->new NullPointerException("Product doesn't exist."));
    }
}