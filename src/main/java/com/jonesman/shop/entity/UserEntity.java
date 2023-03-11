package com.jonesman.shop.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "First name cannot be empty")
    @Size(min = 4, message = "First name length cannot be less than 6")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 4, message = "Last name length cannot be less than 6")

    private String lastName;
    private String userName;
    private String email;

    @NotBlank(message = "Telephone number cannot be empty")
    @Pattern(regexp = "\\d+", message = "Telephone number must be a number")
    private String telephone;

    @Size(min = 6, message = "Password length cannot be less than 6")
    private String password;


    private boolean isApproved;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rolesId", referencedColumnName = "id")
    )
    private Collection<RoleEntity> role;


    public <T> UserEntity(String userName, String firstName, String lastName, String email, String telephone, String password, List<T> roleUser) {


    }


    @PrePersist
    private void prePersist() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime now = LocalDateTime.now();

        userName = firstName.trim().toLowerCase() + dtf.format(now);
        password = passwordEncoder.encode(password);
        isApproved = false;
    }
}
