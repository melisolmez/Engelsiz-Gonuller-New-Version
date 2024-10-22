package dev.melis.EngelsizGonuller.service.registration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import dev.melis.EngelsizGonuller.model.User;
import dev.melis.EngelsizGonuller.model.UserType;
import dev.melis.EngelsizGonuller.repository.UserRepository;
import dev.melis.EngelsizGonuller.support.result.OperationResult;
import dev.melis.EngelsizGonuller.support.emialvalidator.EmailValidator;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private UserRepository userRepository;

    private RegistrationServiceRequest user;

    @InjectMocks
    private DefaultRegistrationService service;

    @BeforeEach
    void setUp(){
         user= new RegistrationServiceRequest();
    }

    @Test
    void registerNewUserTest(){

        user.setName("Melis");
        user.setSurname("Olmez");
        user.setEmail("olmezmelis@gmail.com");
        user.setPassword("1234");
        user.setUserType(UserType.valueOf("VOLUNTEER"));

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        assertEquals(OperationResult.SUCCESS, service.register(user).getResult());
    }

    @Test
    void existsUserTest(){

        user.setName("Melis");
        user.setSurname("Olmez");
        user.setEmail("olmezmelis204@gmail.com");
        user.setPassword("1234");
        user.setUserType(UserType.valueOf("VOLUNTEER"));

        var existsUser= new User();
        existsUser.setName(user.getName());
        existsUser.setSurname(user.getSurname());
        existsUser.setEmail(user.getEmail());
        existsUser.setPassword(user.getPassword());
        existsUser.setUserType(UserType.VOLUNTEER);

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(existsUser));

        assertEquals(OperationResult.FAILED,service.register(user).getResult());

    }

    @Test
    void invalidEmailTest(){

        user.setName("Melis");
        user.setSurname("Olmez");
        user.setEmail("olmezmelis204@ilcom");
        user.setPassword("1234");
        user.setUserType(UserType.valueOf("VOLUNTEER"));

        assertFalse(EmailValidator.isValidEmail(user.getEmail()));

    }

    @Test
    void validEmail(){

        user.setName("Melis");
        user.setSurname("Olmez");
        user.setEmail("olmezmelis204@gmail.com");
        user.setPassword("1234");
        user.setUserType(UserType.valueOf("VOLUNTEER"));

        assertTrue(EmailValidator.isValidEmail(user.getEmail()));
    }


}