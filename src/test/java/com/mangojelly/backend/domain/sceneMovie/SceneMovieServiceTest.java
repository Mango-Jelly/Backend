package com.mangojelly.backend.domain.sceneMovie;

import com.mangojelly.backend.domain.room.Room;
import com.mangojelly.backend.domain.scene.Scene;
import com.mangojelly.backend.fixture.RoomFixture;
import com.mangojelly.backend.fixture.SceneFixture;
import com.mangojelly.backend.fixture.SceneMovieFixture;
import com.mangojelly.backend.global.common.S3FileUploader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

@Transactional
@ExtendWith(MockitoExtension.class)
class SceneMovieServiceTest {

    @InjectMocks
    private SceneMovieService sceneMovieService;

    @Mock
    private SceneMovieRepository sceneMovieRepository;

    @Mock
    private S3FileUploader s3FileUploader;

    @Spy
    private SceneMovieMapper sceneMovieMapper;

    @Nested
    @DisplayName("Scene 저장 테스트")
    class saveTest{

        @DisplayName("성공,존재하지 않는 영상이였을 경우")
        @Test
        void saveWhenMovieNotExist() {
            //GIVEN
            Room room = RoomFixture.ROOM_FIXTURE.create();
            Scene scene = SceneFixture.SCENE_FIXTURE1.create();
            MultipartFile multipartFile = new MockMultipartFile("images", (byte[]) null);
            given(s3FileUploader.uploadFile(any(MultipartFile.class),any())).willReturn("movieUrl");
            given(sceneMovieRepository.findByRoomAndScene(any(),any())).willReturn(Optional.empty());

            //WHEN, THEN
            assertDoesNotThrow(()->{
                sceneMovieService.save(room,scene,multipartFile);
            });
        }

        @DisplayName("성공,존재하는 영상을 덮어쓰는 경우")
        @Test
        void saveWhenMovieExist() {
            //GIVEN
            Room room = RoomFixture.ROOM_FIXTURE.create();
            Scene scene = SceneFixture.SCENE_FIXTURE1.create();
            SceneMovie sceneMovie = SceneMovieFixture.SCENE_MOVIE_FIXTURE.create();
            MultipartFile multipartFile = new MockMultipartFile("images", (byte[]) null);

            given(s3FileUploader.uploadFile(any(MultipartFile.class),any())).willReturn("movieUrl");
            given(sceneMovieRepository.findByRoomAndScene(any(),any())).willReturn(Optional.of(sceneMovie));

            //WHEN, THEN
            assertDoesNotThrow(()->{
                sceneMovieService.save(room,scene,multipartFile);
            });
        }
    }

}