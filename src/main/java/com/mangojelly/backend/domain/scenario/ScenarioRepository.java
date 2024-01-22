package com.mangojelly.backend.domain.scenario;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

interface ScenarioRepository extends MongoRepository<Scenario,String> {
    List<Scenario> findAllByIdContaining(String script);

}
