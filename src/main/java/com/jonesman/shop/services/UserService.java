package com.jonesman.shop.services;

import com.jonesman.shop.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

//    void save(User userRegistrationDto);


    void saveUser(UserEntity userEntity);


}
