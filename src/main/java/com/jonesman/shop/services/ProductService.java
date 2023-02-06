package com.jonesman.shop.services;

import com.jonesman.shop.entity.ProductEntity;
import com.jonesman.shop.model.Product;

import java.util.List;


public interface ProductService {
    Product createProduct(Product product);


    List<ProductEntity> getAllProducts();

    void deleteProductById(long id);

}
