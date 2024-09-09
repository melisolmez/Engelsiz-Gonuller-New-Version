package dev.melis.EngelsizGonuller.service.registration;

import dev.melis.EngelsizGonuller.model.ConfirmationToken;
import dev.melis.EngelsizGonuller.model.User;
import dev.melis.EngelsizGonuller.model.UserType;
import dev.melis.EngelsizGonuller.repository.ConfirmationTokenRepository;
import dev.melis.EngelsizGonuller.repository.UserRepository;
import dev.melis.EngelsizGonuller.service.email.EmailService;
import dev.melis.EngelsizGonuller.support.CreationResult;
import dev.melis.EngelsizGonuller.support.OperationFailureReason;
import dev.melis.EngelsizGonuller.support.emialvalidator.EmailValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DefaultRegistrationService implements RegistrationService{

    private final UserRepository repository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;

    public DefaultRegistrationService(UserRepository repository, ConfirmationTokenRepository confirmationTokenRepository, EmailService emailService) {
        this.repository = repository;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.emailService = emailService;
    }

    // security eklendiğinde password hashlenmeli
    @Override
    public CreationResult register(RegistrationServiceRequest request) {
        Optional<User> userOptional= repository.findByEmail(request.getEmail());
        if(userOptional.isPresent()){
            return CreationResult.failure(OperationFailureReason.CONFLICT,"User has already registered");
        }
        var user= new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        if(!EmailValidator.isValidEmail(request.getEmail())){
            return CreationResult.failure(OperationFailureReason.CONFLICT,"Invalid email");
        }
        user.setEmail(request.getEmail());
        user.setDateOfRegistration(LocalDate.now());
        user.setUserPassword(request.getPassword());
        if(request.getUserType()== UserType.VOLUNTEER){
            user.setUserType(UserType.VOLUNTEER);
        }else{
            user.setUserType(UserType.DISABLED_INDIVIDUAL);
        }
        repository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
            +"http://localhost:8085/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendMail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());


        return CreationResult.success();
    }

    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token= confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token!=null){
            Optional<User> user= repository.findByEmail(token.getUser().getEmail());
            repository.save(user.get());
            return ResponseEntity.ok("Email verified successfully!");
        }

        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }
}
