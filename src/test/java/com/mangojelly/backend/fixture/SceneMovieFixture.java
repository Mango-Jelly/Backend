package com.mangojelly.backend.fixture;

import com.mangojelly.backend.domain.sceneMovie.SceneMovie;

public enum SceneMovieFixture {
    SCENE_MOVIE_FIXTURE("sample/movie.mp4");

    private final String movie;

    SceneMovieFixture(String movie){
        this.movie = movie;
    }

    public SceneMovie create(String movie){
        return SceneMovie.builder()
                .room(RoomFixture.ROOM_FIXTURE.create())
                .scene(SceneFixture.SCENE_FIXTURE1.create())
                .address(movie).build();
    }

    public SceneMovie create(){
        return SceneMovie.builder()
                .room(RoomFixture.ROOM_FIXTURE.create())
                .scene(SceneFixture.SCENE_FIXTURE1.create())
                .address(movie).build();
    }
}
