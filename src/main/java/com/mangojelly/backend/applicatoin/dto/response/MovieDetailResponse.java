package com.mangojelly.backend.applicatoin.dto.response;

import com.mangojelly.backend.domain.movie.Movie;
import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.util.List;

public record MovieDetailResponse(String title, String scriptName, boolean isPublic, String urlThumbnail,
                                  String department, String party, String videoAddress, LocalDateTime createAt) {
    public static MovieDetailResponse from(Movie movie) {
        return new MovieDetailResponse(movie.getTitle(), movie.getScript().getName(), movie.isVisible(), movie.getScript().getImage(),
                movie.getDpt(), movie.getParty(), movie.getAddress(), movie.getCreateAt());
    }

    public MovieDetailResponse(String title, String scriptName, boolean isPublic, String urlThumbnail,
                               String department, String party, String videoAddress, LocalDateTime createAt) {
        this.title = title;
        this.scriptName = scriptName;
        this.isPublic = isPublic;
        this.urlThumbnail = urlThumbnail;
        this.department = department;
        this.party = party;
        this.videoAddress = videoAddress;
        this.createAt = createAt;
    }
}


/*
			"title" : String,
			"scriptName" : String,
			"isPublic" : boolean,
			"urlThumbnail" : String,
			"department" : String,
			"party" : String,
			"videoAddress" : String,
			"createAt" : Date
 */