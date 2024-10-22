package dev.melis.EngelsizGonuller.service.registration;

import dev.melis.EngelsizGonuller.support.result.CreationResult;
import jakarta.validation.Valid;

public interface RegistrationService {

  CreationResult register(@Valid RegistrationServiceRequest request);

}
