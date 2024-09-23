package dev.melis.EngelsizGonuller.controller.registration;

import dev.melis.EngelsizGonuller.model.UserType;
import dev.melis.EngelsizGonuller.service.registration.RegistrationServiceRequest;

public record RegistrationControllerRequest(
        String name,
        String surname,
        String email,
        String password,
        UserType userType
) {

    RegistrationServiceRequest toServiceRequest(){
        return new RegistrationServiceRequest()
                .setEmail(email)
                .setName(name)
                .setSurname(surname)
                .setPassword(password)
                .setUserType(userType);
    }
}
