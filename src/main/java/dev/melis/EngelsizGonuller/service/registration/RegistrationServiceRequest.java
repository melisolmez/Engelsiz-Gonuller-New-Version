package dev.melis.EngelsizGonuller.service.registration;

import dev.melis.EngelsizGonuller.model.UserType;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public class RegistrationServiceRequest {

    @Email
    private String email;

    @Length(min = 8)
    private String password;
    private String name;
    private String surname;
    private UserType userType;
    private LocalDate dateOfRegistration;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public UserType getUserType() {
        return userType;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public RegistrationServiceRequest setEmail(String email){
        this.email=email;
        return this;
    }

    public RegistrationServiceRequest setPassword(String password){
        this.password=password;
        return this;
    }

    public RegistrationServiceRequest setName(String name){
        this.name=name;
        return this;
    }

    public RegistrationServiceRequest setSurname(String surname){
        this.surname=surname;
        return this;
    }

    public RegistrationServiceRequest setUserType(UserType userType){
        this.userType=userType;
        return this;
    }

}
