package dev.melis.EngelsizGonuller.service.registration;

import dev.melis.EngelsizGonuller.model.User;
import dev.melis.EngelsizGonuller.support.CreationResult;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {

  CreationResult register(@Valid RegistrationServiceRequest request);

}
