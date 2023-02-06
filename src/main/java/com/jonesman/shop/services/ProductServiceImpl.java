package com.jonesman.shop.services;

import com.jonesman.shop.entity.ProductEntity;
import com.jonesman.shop.model.Product;
import com.jonesman.shop.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        ProductEntity   productEntity = new ProductEntity();

        BeanUtils.copyProperties(product, productEntity);
        productRepository.save(productEntity);
         return product;
    }

    @Override
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }


}
