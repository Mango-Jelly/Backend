package com.mangojelly.backend.applicatoin.controller;

import com.mangojelly.backend.applicatoin.dto.response.GetAllMovieResponse;
import com.mangojelly.backend.applicatoin.facade.MovieFacade;
import com.mangojelly.backend.global.common.Authenticated;
import com.mangojelly.backend.global.response.api.ApiResponse;
import com.mangojelly.backend.global.response.api.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {
    private final MovieFacade movieFacade;

    @PostMapping("/scene")
    public ResponseEntity<ApiResponse<Void>> saveSceneMovie(@Authenticated int memberId, @RequestParam(value = "sceneId") int sceneId, @RequestParam(value = "movie") MultipartFile movieFile){
        movieFacade.saveSceneMovie(memberId,sceneId, movieFile);
        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_MOVIE_SCENE_CREATE));
    }


    @GetMapping("/list")
    public ResponseEntity<ApiResponse<GetAllMovieResponse>> getMovies(){
        GetAllMovieResponse response = movieFacade.getAllMovies();
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(ResponseCode.API_SUCCESS_MOVIES_READ, response));
    }


}
