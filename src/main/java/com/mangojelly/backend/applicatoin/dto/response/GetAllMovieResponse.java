package com.mangojelly.backend.applicatoin.dto.response;

import com.mangojelly.backend.domain.movie.Movie;

import java.util.ArrayList;
import java.util.List;

public record GetAllMovieResponse(List<GetMovieResponse> videos) {

    public GetAllMovieResponse(List<GetMovieResponse> videos) {this.videos = videos;}

    public static GetAllMovieResponse of(List<Movie> videos){
        List<GetMovieResponse> response = new ArrayList<>();
        for(Movie movie : videos){
            response.add(GetMovieResponse.from(movie));
        }
        return new GetAllMovieResponse(response);
    }
}
