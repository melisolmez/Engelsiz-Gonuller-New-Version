package dev.melis.EngelsizGonuller.model;


import jakarta.persistence.*;

import java.time.LocalDate;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

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

    public User(){
        super();
        this.enabled=false;
    }

}
