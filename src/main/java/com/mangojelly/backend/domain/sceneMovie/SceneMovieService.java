package com.mangojelly.backend.domain.sceneMovie;

import com.mangojelly.backend.domain.room.Room;
import com.mangojelly.backend.domain.scene.Scene;
import com.mangojelly.backend.global.common.S3FileUploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class SceneMovieService {
    private final SceneMovieRepository sceneMovieRepository;
    private final SceneMovieMapper sceneMovieMapper;
    private final S3FileUploader s3FileUploader;

    public SceneMovie save(Room room, Scene scene, MultipartFile movieFile){
        String movie = s3FileUploader.uploadFile(movieFile,"scene");
        Optional<SceneMovie> sceneMovie = sceneMovieRepository.findByRoomAndScene(room,scene);
        if(sceneMovie.isPresent()){
            sceneMovie.get().setAddress(movie);
            return sceneMovie.get();
        }
        return sceneMovieRepository.save(sceneMovieMapper.toEntity(room,scene,movie));

    }
}
