package com.mangojelly.backend.applicatoin.dto.response;

import com.mangojelly.backend.domain.movie.Movie;
import org.joda.time.DateTime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record MovieDetailResponse(String title, String scriptName, boolean isPublic, String urlThumbnail,
                                  String department, List<String> party, List<String> roles, String videoAddress,
                                  LocalDateTime createAt) {
    public static MovieDetailResponse of(Movie movie) {

        String[] partyArray = movie.getParty().split(",");
        List<String> partyList = new ArrayList<>();
        List<String> roleList = new ArrayList<>();
        for (int i = 1; i < partyArray.length; i = i + 2) {
            if(!partyArray[i].equals(" ")){
                partyList.add(partyArray[i - 1]);
                roleList.add(partyArray[i]);
            }
        }

        return new MovieDetailResponse(movie.getTitle(), movie.getScript().getName(), movie.isVisible(), movie.getScript().getImage(),
                movie.getDpt(), partyList, roleList, movie.getAddress(), movie.getCreateAt());
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