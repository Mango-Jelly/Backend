package com.mangojelly.backend.applicatoin.runner;

import com.mangojelly.backend.applicatoin.facade.InitialFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class MangoJellyApplicationRunner implements ApplicationRunner {
    private final InitialFacade initialFacade;

    @Override
    public void run(ApplicationArguments args) throws IOException {
        initialFacade.run();
    }

}
