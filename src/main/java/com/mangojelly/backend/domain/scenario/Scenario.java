package com.mangojelly.backend.domain.scenario;

import jakarta.persistence.Id;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Document(collection = "script")
public class Scenario {
    @Id
    private String id;
    private List<String> scenario;

    private Scenario(String id, List<String> scenario){
        this.id = id;
        this.scenario = scenario;
    }

    static public Scenario from(String id, List<String> scenario){
        return new Scenario(id,scenario);
    }
}
