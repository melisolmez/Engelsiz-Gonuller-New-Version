package dev.melis.EngelsizGonuller.service.registration;

import dev.melis.EngelsizGonuller.model.User;
import dev.melis.EngelsizGonuller.model.UserType;
import dev.melis.EngelsizGonuller.repository.UserRepository;
import dev.melis.EngelsizGonuller.support.CreationResult;
import dev.melis.EngelsizGonuller.support.OperationFailureReason;
import dev.melis.EngelsizGonuller.support.emialvalidator.EmailValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DefaultRegistrationService implements RegistrationService{

    private final UserRepository repository;


    public DefaultRegistrationService(UserRepository repository) {
        this.repository = repository;
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
        return CreationResult.success();
    }

}
