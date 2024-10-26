package com.enterprisemanager.backend.infrastructure.repositories.jwttoken;

import com.enterprisemanager.backend.domain.entities.security.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtTokenRepository extends JpaRepository<JwtToken,Long> {
    Optional<JwtToken> findByToken(String jwt);
}
