package dev.melis.EngelsizGonuller.service.email;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


import dev.melis.EngelsizGonuller.model.VerificationToken;
import dev.melis.EngelsizGonuller.repository.UserRepository;
import dev.melis.EngelsizGonuller.repository.VerificationTokenRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VerificationTokenServiceTest {

    @Mock
    VerificationTokenRepository verificationTokenRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    VerificationTokenService verificationTokenService;

    @BeforeEach
    void setUp(){

    }

    @Test
    void  nullTokenTest(){

        String token= "123";
        when(verificationTokenRepository.findByToken(token)).thenReturn(Optional.empty());
        assertFalse(verificationTokenService.validateVerificationToken(token));
    }

    @Test
    void expiredTokenTest(){

        String token="123";

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, -1);
        Date expiryDate = calendar.getTime();

        VerificationToken expiredToken= new VerificationToken();
        expiredToken.setToken(token);
        expiredToken.setExpiryDate(expiryDate);

        when(verificationTokenRepository.findByToken(token)).thenReturn(Optional.of(expiredToken));
        boolean result = verificationTokenService.validateVerificationToken(token);
        assertFalse(result);

    }

    @Test
    void validTokenTest(){

        String token="123";

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 0);
        Date expiryDate = calendar.getTime();

        VerificationToken testToken= new VerificationToken();
        testToken.setToken(token);
        testToken.setExpiryDate(expiryDate);

        when(verificationTokenRepository.findByToken(token)).thenReturn(Optional.of(testToken));
        boolean result = verificationTokenService.validateVerificationToken(token);
        assertTrue(result);

    }


}