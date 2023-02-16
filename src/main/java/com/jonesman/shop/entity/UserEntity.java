package com.jonesman.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames =  "username") )
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    private String email;

    private String telephone;

    private String userName;

    private String password;

    private boolean isApproved;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    @JoinTable(
            name = "users _roles",
            joinColumns= @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_sid", referencedColumnName = "id")
    )
    private Collection<RoleEntity> role;

    public <T> UserEntity(String userName, String firstName, String lastName, String email, String telephone, String password, List<T> roleUser) {


    }
    @PrePersist
    private void prePersist(){
          DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
          LocalDateTime now = LocalDateTime.now();
        userName = firstName.trim().toLowerCase() + dtf.format(now);
        isApproved = false;
    }
}
