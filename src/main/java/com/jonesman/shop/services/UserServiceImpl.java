package com.jonesman.shop.services;

import com.jonesman.shop.entity.RoleEntity;
import com.jonesman.shop.entity.UserEntity;
import com.jonesman.shop.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }


    @Override
    public void saveUser(UserEntity userEntity) {


        System.out.println("service values " + userEntity + "\n");
        userEntity.setRole(List.of(new RoleEntity("ROLE_USER")));
        this.userRepository.save(userEntity);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(username);
        System.out.println("username : " + user.getUserName());

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        Collection<? extends GrantedAuthority> authorities = mapRolesToAuthorities(user.getRole());

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
