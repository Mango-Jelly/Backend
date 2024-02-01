package com.mangojelly.backend.domain.movie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    @InjectMocks
    private MovieService movieService;
    @Mock
    private MovieRepository movieRepository;

    @DisplayName("movie get 테스트")
    @Nested
    class get{
        @Test
        @DisplayName("성공")
        void whenSuccess(){
            // given

        }

    }

}
