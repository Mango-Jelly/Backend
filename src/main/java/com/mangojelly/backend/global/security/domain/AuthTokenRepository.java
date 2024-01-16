package com.mangojelly.backend.global.security.domain;

import org.springframework.data.repository.CrudRepository;

public interface AuthTokenRepository extends CrudRepository<AuthToken, Integer> {
}
