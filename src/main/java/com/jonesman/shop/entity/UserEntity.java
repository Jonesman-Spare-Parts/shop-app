package com.jonesman.shop.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Data
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    private String email;

    private String userName;

    private String password;

    @CreatedDate
    private Date createdAdded;

    @LastModifiedDate
    private Date updatedAt;



}
