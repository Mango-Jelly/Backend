package com.mangojelly.backend.global.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Transactional
@SpringBootTest
class FileDownLoaderTest {
    @Autowired
    private FileDownLoader fileDownLoader;

    @DisplayName("파일 로드 테스트")
    @Test
    void loadFileTest() {
        assertDoesNotThrow(()->{
            fileDownLoader.loadFile("https://mongo-jelly.s3.ap-northeast-2.amazonaws.com/frontSampleVideo.mp4","util/videos/");
        });
    }
}