package com.mangojelly.backend.applicatoin.dto.response;

import com.mangojelly.backend.domain.movie.Movie;

public record GetOneMovieResponse(MovieDetailResponse movieDetailResponse) {
    public GetOneMovieResponse(MovieDetailResponse movieDetailResponse) {
        this.movieDetailResponse = movieDetailResponse;
    }

    public static GetOneMovieResponse of(Movie movie) {
        MovieDetailResponse response = MovieDetailResponse.from(movie);
        return new GetOneMovieResponse(response);
    }
}
