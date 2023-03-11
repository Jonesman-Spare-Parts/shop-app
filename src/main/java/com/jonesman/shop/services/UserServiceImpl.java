package com.jonesman.shop.services;

import com.jonesman.shop.entity.RoleEntity;
import com.jonesman.shop.entity.UserEntity;
import com.jonesman.shop.repository.DailyTotalRepository;
import com.jonesman.shop.repository.ProductRepository;
import com.jonesman.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
        userEntity.setRole(List.of(new RoleEntity("ROLE_ADMIN")));
        this.userRepository.save(userEntity);
    }

    @Override
    public void deleteUserById(long id) {
        System.out.println("delete impl id + " + id);
        this.userRepository.deleteById(id);
    }

    @Override
    public void updateUserApproval(long id) {
//        UserEntity user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        user.setApproved(isApproved);
//        userRepository.save(user);
        System.out.println("serviceimpl id + " + id);
    }

    @Override
    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    public Page<UserEntity> findPagination(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.userRepository.findAll(pageable);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findByUsername(String username) {
        return null;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUserName(username);
        System.out.println("username : " + user.getUserName());

        if (user == null || !user.isApproved()) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        Collection<? extends GrantedAuthority> authorities = mapRolesToAuthorities(user.getRole());

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), authorities);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<RoleEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
