package dev.melis.EngelsizGonuller.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUserJob() {
        return job;
    }

    public void setUserJob(String userJob) {
        this.job = userJob;
    }

    public String getAboutUser() {
        return about;
    }

    public void setAboutUser(String aboutUser) {
        this.about = aboutUser;
    }

    public String getUserPassword() {
        return password;
    }

    public void setUserPassword(String userPassword) {
        this.password = userPassword;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

}
