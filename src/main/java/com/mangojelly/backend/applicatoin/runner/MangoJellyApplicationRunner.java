package com.mangojelly.backend.applicatoin.runner;

import com.mangojelly.backend.applicatoin.facade.InitialFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.io.IOException;

@RequiredArgsConstructor
public class MangoJellyApplicationRunner implements ApplicationRunner {
    private final InitialFacade initialFacade;
    @Override
    public void run(ApplicationArguments args) throws IOException {
        initialFacade.run();
    }

}
