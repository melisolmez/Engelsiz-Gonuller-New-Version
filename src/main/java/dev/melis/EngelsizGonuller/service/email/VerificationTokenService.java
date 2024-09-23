package dev.melis.EngelsizGonuller.service.email;

import dev.melis.EngelsizGonuller.model.User;
import dev.melis.EngelsizGonuller.model.VerificationToken;
import dev.melis.EngelsizGonuller.repository.UserRepository;
import dev.melis.EngelsizGonuller.repository.VerificationTokenRepository;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenService {

    private final VerificationTokenRepository tokenRepository;
    private final UserRepository userRepository;

    public VerificationTokenService(VerificationTokenRepository tokenRepository, UserRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    public String generateVerificationToken(){
        return UUID.randomUUID().toString();
    }

    public boolean validateVerificationToken(String token){
        Optional<VerificationToken> verificationToken = tokenRepository.findByToken(token);
        if(verificationToken.isEmpty()){
            return false;
        }

        Calendar calendar = Calendar.getInstance();
        if(verificationToken.get().getExpiryDate().getTime() - calendar.getTime().getTime() <=0){
            return false;
        }

        User user= verificationToken.get().getUser();
        user.setEnabled(true);
        userRepository.save(user);

        return true;

    }


}
