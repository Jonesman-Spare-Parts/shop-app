package com.jonesman.shop.services;

import com.jonesman.shop.entity.ProductEntity;
import com.jonesman.shop.model.Product;
import com.jonesman.shop.repository.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ProductEntity getProductById(long id) {
        Optional<ProductEntity> optional = productRepository.findById(id);

        ProductEntity productEntity = null;

        if (optional.isPresent()){
            productEntity = optional.get();
        }else {
            throw new RuntimeException("Product not found with id :: " + id);
        }
        return productEntity;
    }


    @Override
    public void saveProduct(ProductEntity productEntity) {

        this.productRepository.save(productEntity);
    }


    @Override
    public void deleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public Page<ProductEntity> findPagination(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return  this.productRepository.findAll(pageable);
    }


}
