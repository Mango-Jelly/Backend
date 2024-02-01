package com.mangojelly.backend.applicatoin.dto.response;

import com.mangojelly.backend.domain.movie.Movie;

public record GetMovieResponse(int videoId, String title, String urlThumbnail, String department, boolean isPublic) {

    public static GetMovieResponse from(Movie movie){
        return new GetMovieResponse(movie.getId(), movie.getTitle(), movie.getScript().getImage(), movie.getDpt(), movie.isVisible());
    }

    public GetMovieResponse(int videoId, String title, String urlThumbnail, String department, boolean isPublic){
        this.videoId = videoId;
        this.title = title;
        this.urlThumbnail = urlThumbnail;
        this.department = department;
        this.isPublic = isPublic;
    }
}
