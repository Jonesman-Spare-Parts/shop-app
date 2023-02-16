package com.jonesman.shop.services;

import com.jonesman.shop.entity.UserEntity;
import com.jonesman.shop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }


//    @Override
//    public UserEntity save(User userRegistrationDto) {
//
//        UserEntity userEntity = new UserEntity(
//                userRegistrationDto.getUserName(), userRegistrationDto.getFirstName(),userRegistrationDto.getLastName(),
//                userRegistrationDto.getEmail(), userRegistrationDto.getTelephone(), userRegistrationDto.getPassword(),
//                Arrays.asList(new RoleEntity("ROLE_USER"))
//        );
//
//        System.out.println("service values " + userEntity + "\n");
//
//        userRepository.save(userEntity);
//    }

    @Override
    public void saveUser(UserEntity userEntity) {


        System.out.println("service values " + userEntity + "\n");

        this.userRepository.save(userEntity);
    }
}
