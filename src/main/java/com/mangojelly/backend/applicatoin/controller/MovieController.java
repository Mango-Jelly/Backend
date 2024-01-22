package com.mangojelly.backend.applicatoin.controller;

import com.mangojelly.backend.applicatoin.facade.MovieFacade;
import com.mangojelly.backend.global.common.Authenticated;
import com.mangojelly.backend.global.response.api.ApiResponse;
import com.mangojelly.backend.global.response.api.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/movie")
public class MovieController {
    private final MovieFacade movieFacade;

    @PostMapping("/scene")
    public ResponseEntity<ApiResponse<Void>> saveSceneMovie(@Authenticated int memberId,@RequestParam(value = "sceneId") int sceneId, @RequestParam(value = "movie") MultipartFile movieFile){
        movieFacade.saveSceneMovie(memberId,sceneId, movieFile);
        return ResponseEntity.ok(new ApiResponse<>(ResponseCode.API_SUCCESS_MOVIE_SCENE_CREATE));
    }
}
