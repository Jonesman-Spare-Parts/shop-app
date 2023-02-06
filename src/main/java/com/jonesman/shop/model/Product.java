package com.jonesman.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
     private long id;
    private String productCode;
    private String productName;

    private float productPrice;

    private int productUnit;

}
