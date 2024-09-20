package dev.melis.EngelsizGonuller.controller;

import dev.melis.EngelsizGonuller.service.email.VerificationTokenService;
import dev.melis.EngelsizGonuller.service.registration.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class RegistrationController {

    private final RegistrationService registrationService;
    private final VerificationTokenService verificationTokenService;

    public RegistrationController(RegistrationService registrationService, VerificationTokenService verificationTokenService) {
        this.registrationService = registrationService;
        this.verificationTokenService = verificationTokenService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegistrationControllerRequest request){
        var result= registrationService.register(request.toServiceRequest());
        if(!result.isSuccess()){
            return new ResponseEntity<>("User has already registered",HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyEmail(@RequestParam("token") String token){
        boolean result= verificationTokenService.validateVerificationToken(token);
        if(result){
            return new ResponseEntity<>("Verification token validated",HttpStatus.OK);
        }

        return new ResponseEntity<>("Verification token not valid",HttpStatus.CONFLICT);
    }

}
