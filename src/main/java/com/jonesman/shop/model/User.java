package com.jonesman.shop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String firstName;
    private String lastName;

    private String email;

    private String telephone;

    private String userName;

    private String password;
    private boolean isApproved;


}
