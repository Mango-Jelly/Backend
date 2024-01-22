package com.mangojelly.backend.applicatoin.controller;

import com.mangojelly.backend.applicatoin.facade.MovieFacade;
import com.mangojelly.backend.global.security.support.TokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@Transactional
@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    private static MockMvc mockMvc;

    @InjectMocks
    private static MovieController movieController;

    @Mock
    private MovieFacade movieFacade;

    @Mock
    private TokenProvider tokenProvider;

    private final String token = "token";

    @BeforeEach
    public void setUpMockMvc() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    void saveSceneMovie() throws Exception {
//        given(movieFacade.saveSceneMovie(any(),any(),any(MultipartFile.class))).;
        given(tokenProvider.validateToken(any())).willReturn(true);
        mockMvc.perform(
                        post("/api/v1/movie/scene")
                            .queryParam("sceneId", String.valueOf(1))
                            .queryParam("movie","movieName")
                            .header("Authorization","Bearer "+token))
                .andDo(print());
    }
}