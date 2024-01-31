package com.mangojelly.backend.domain.authToken;

import org.springframework.data.mongodb.repository.MongoRepository;

interface AuthTokenRepository extends MongoRepository<AuthToken, Integer> {
}
