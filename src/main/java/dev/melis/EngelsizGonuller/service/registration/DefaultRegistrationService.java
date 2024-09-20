package dev.melis.EngelsizGonuller.service.registration;

import dev.melis.EngelsizGonuller.model.User;
import dev.melis.EngelsizGonuller.model.UserType;
import dev.melis.EngelsizGonuller.model.VerificationToken;
import dev.melis.EngelsizGonuller.repository.UserRepository;
import dev.melis.EngelsizGonuller.repository.VerificationTokenRepository;
import dev.melis.EngelsizGonuller.service.email.EmailService;
import dev.melis.EngelsizGonuller.service.email.VerificationTokenService;
import dev.melis.EngelsizGonuller.support.CreationResult;
import dev.melis.EngelsizGonuller.support.OperationFailureReason;
import dev.melis.EngelsizGonuller.support.emialvalidator.EmailValidator;
import dev.melis.EngelsizGonuller.support.passwordencoder.PasswordEncoderAdaptor;
import java.util.Calendar;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DefaultRegistrationService implements RegistrationService{

    private final UserRepository repository;
    private final VerificationTokenService tokenService;
    private final EmailService emailService;
    private final VerificationTokenRepository verificationTokenRepository;


    public DefaultRegistrationService(UserRepository repository, VerificationTokenService tokenService, EmailService emailService,
        VerificationTokenRepository verificationTokenRepository) {
        this.repository = repository;
        this.tokenService = tokenService;
        this.emailService = emailService;
        this.verificationTokenRepository = verificationTokenRepository;
    }

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
        PasswordEncoderAdaptor passwordEncoderAdaptor= new PasswordEncoderAdaptor();
        String hashedPassword = passwordEncoderAdaptor.encode(request.getPassword());
        user.setPassword(hashedPassword);
        if(request.getUserType()== UserType.VOLUNTEER){
            user.setUserType(UserType.VOLUNTEER);
        }else{
            user.setUserType(UserType.DISABLED_INDIVIDUAL);
        }

        repository.save(user);

        String token= tokenService.generateVerificationToken();
        VerificationToken verificationToken= new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        Calendar calendar= Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);
        verificationToken.setExpiryDate(calendar.getTime());
        verificationTokenRepository.save(verificationToken);
        emailService.sendVerificationEmail(user.getEmail(),token);

        return CreationResult.success();
    }

}
