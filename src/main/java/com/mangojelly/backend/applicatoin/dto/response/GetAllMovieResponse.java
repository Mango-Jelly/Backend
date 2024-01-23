package com.mangojelly.backend.applicatoin.dto.response;

import com.mangojelly.backend.domain.movie.Movie;

import java.util.ArrayList;
import java.util.List;

public record GetAllMovieResponse(List<GetMovieResponse> movies) {

    public GetAllMovieResponse(List<GetMovieResponse> movies) {this.movies = movies;}

    public static GetAllMovieResponse of(List<Movie> movies){
        List<GetMovieResponse> response = new ArrayList<>();
        for(Movie movie : movies){
            response.add(GetMovieResponse.from(movie));
        }
        return new GetAllMovieResponse(response);
    }
}
