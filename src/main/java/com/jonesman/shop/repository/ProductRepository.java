package com.jonesman.shop.repository;

import com.jonesman.shop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    long countById(long id);

    @Query("SELECT SUM(p.productPrice) FROM ProductEntity p")
    Double getTotalProductPrice();
}
