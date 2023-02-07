package com.jonesman.shop.services;

import com.jonesman.shop.entity.ProductEntity;
import com.jonesman.shop.model.Product;

import java.util.List;


public interface ProductService {


    List<ProductEntity> getAllProducts();

   ProductEntity getProductById(long id);

    void saveProduct(ProductEntity productEntity);

    void deleteProductById(long id);



}
