package com.jonesman.shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Data
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String productCode;
    private String productName;

    private float productPrice;

    private int productUnit;

    @CreatedDate
    private Date createdAdded;

    @LastModifiedDate
    private Date updatedAt;


}
