package dev.melis.EngelsizGonuller.service.authentication;

import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import dev.melis.EngelsizGonuller.support.result.AuthenticationResult;

public interface AuthenticationService {

    AuthenticationResult authenticate(AuthenticationServiceRequest authenticationRequest);
}
