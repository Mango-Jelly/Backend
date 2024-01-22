package com.mangojelly.backend.applicatoin.controller;

import com.mangojelly.backend.applicatoin.dto.response.GetAllMovieResponse;
import com.mangojelly.backend.applicatoin.facade.MovieFacade;
import com.mangojelly.backend.domain.movie.Movie;
import com.mangojelly.backend.global.response.api.ApiResponse;
import com.mangojelly.backend.global.response.api.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {
    final private MovieFacade movieFacade;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<GetAllMovieResponse>> getMovies(){
        GetAllMovieResponse response = movieFacade.getAllMovies();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.API_SUCCESS_MOVIES_GET, response));
    }

}
