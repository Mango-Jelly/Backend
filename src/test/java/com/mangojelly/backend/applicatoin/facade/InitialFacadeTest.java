package com.mangojelly.backend.applicatoin.facade;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class InitialFacadeTest {
    @Autowired
    private InitialFacade initialFacade;

    @Test
    void run() throws IOException {
        assertThrows(RuntimeException.class,()->{
            initialFacade.run();
        });
    }
}