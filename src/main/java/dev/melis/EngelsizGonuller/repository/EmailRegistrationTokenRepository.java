package dev.melis.EngelsizGonuller.repository;

import dev.melis.EngelsizGonuller.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRegistrationTokenRepository extends JpaRepository<VerificationToken,Long> {

    VerificationToken findByToken(String token);
}
