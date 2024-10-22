package dev.melis.EngelsizGonuller.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="surname", nullable = false)
    private String surname;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="birthday")
    private String birthday;

    @Column(name = "job")
    private String job;

    @Lob
    @Column(name = "about",columnDefinition = "text")
    private String about;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "dateofregistration",nullable = false)
    private LocalDate dateOfRegistration;

    @Column(name = "usertype",nullable = false)
    private UserType userType;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name= "role")
    private Role role;

    public User(){
        super();
        this.enabled=false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
