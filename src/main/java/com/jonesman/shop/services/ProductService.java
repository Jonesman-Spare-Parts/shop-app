package com.jonesman.shop.services;

import com.jonesman.shop.entity.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ProductService {


    List<ProductEntity> getAllProducts();

    ProductEntity getProductById(long id);

    void saveProduct(ProductEntity productEntity);

    void deleteProductById(long id);


    Page<ProductEntity> findPagination(int pageNo, int pageSize, String sortField, String sortDirection);

}
