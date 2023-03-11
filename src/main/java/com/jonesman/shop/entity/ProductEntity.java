package com.jonesman.shop.entity;

import com.jonesman.shop.repository.DailyTotalRepository;
import jakarta.persistence.*;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
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
    private double productPrice;
    private int productUnit;
    @CreationTimestamp
    private Date createdAdded;


}
