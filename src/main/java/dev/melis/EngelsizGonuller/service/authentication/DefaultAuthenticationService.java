package dev.melis.EngelsizGonuller.service.authentication;

import java.util.Optional;

import dev.melis.EngelsizGonuller.config.JwtService;
import dev.melis.EngelsizGonuller.model.User;
import dev.melis.EngelsizGonuller.repository.UserRepository;
import dev.melis.EngelsizGonuller.support.passwordencoder.PasswordEncoderAdaptor;
import dev.melis.EngelsizGonuller.support.result.AuthenticationResult;
import dev.melis.EngelsizGonuller.support.result.OperationFailureReason;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class DefaultAuthenticationService implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoderAdaptor passwordEncoderAdaptor;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public DefaultAuthenticationService(UserRepository userRepository, PasswordEncoderAdaptor passwordEncoderAdaptor, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoderAdaptor = passwordEncoderAdaptor;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResult authenticate(AuthenticationServiceRequest authenticationRequest) {
        var user = userRepository.findByEmail(authenticationRequest.getEmail());

        if(user.isEmpty()) {
            return AuthenticationResult.failure(OperationFailureReason.NOT_FOUND,"User not found");
        }
        if(!user.get().isEnabled()){
            return AuthenticationResult.failure(OperationFailureReason.CONFLICT,"User is disabled.Please verify your email");
        }
        if(!passwordEncoderAdaptor.matches(authenticationRequest.getPassword(), user.get().getPassword())){
            return AuthenticationResult.failure(OperationFailureReason.UNAUTHORIZED,"Incorrect password");
        }

        var jwtToken = jwtService.generateToken(user.get());
        return AuthenticationResult.success(jwtToken);
    }
}
