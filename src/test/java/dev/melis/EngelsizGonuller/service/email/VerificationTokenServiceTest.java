package dev.melis.EngelsizGonuller.service.email;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;


import dev.melis.EngelsizGonuller.repository.UserRepository;
import dev.melis.EngelsizGonuller.repository.VerificationTokenRepository;
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


}