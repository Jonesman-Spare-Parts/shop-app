package com.jonesman.shop.services;

import com.jonesman.shop.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

//    void save(User userRegistrationDto);


    void saveUser(UserEntity userEntity);

    void deleteUserById(long id);

    public void updateUserApproval(long id);

    public UserEntity getUserById(Long id);

    Page<UserEntity> findPagination(int pageNo, int pageSize, String sortField, String sortDirection);

    List<UserEntity> getAllUsers();

    public UserEntity findByUsername(String username);
}
