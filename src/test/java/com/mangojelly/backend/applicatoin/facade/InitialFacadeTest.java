package com.mangojelly.backend.applicatoin.facade;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InitialFacadeTest {
    @Autowired
    private InitialFacade initialFacade;

    @Test
    void run() throws IOException {
        assertDoesNotThrow(()->{
            initialFacade.run();
        });
    }
}