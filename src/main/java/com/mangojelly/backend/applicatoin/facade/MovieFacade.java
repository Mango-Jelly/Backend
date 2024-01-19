package com.mangojelly.backend.applicatoin.facade;

import com.mangojelly.backend.domain.movie.Movie;
import com.mangojelly.backend.domain.movie.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MovieFacade {
    private final MovieService movieService;

    /**
     * 공개 연극 리스트 메소드
     */
    public List<Movie> getAllMovies(){
        return movieService.findMovies();
    }
}
