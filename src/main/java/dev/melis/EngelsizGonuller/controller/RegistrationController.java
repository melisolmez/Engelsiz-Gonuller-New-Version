package dev.melis.EngelsizGonuller.controller;

import dev.melis.EngelsizGonuller.service.registration.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationControllerRequest request){
        var result= registrationService.register(request.toServiceRequest());
        if(!result.isSuccess()){
            return new ResponseEntity<>("User has already registered",HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
