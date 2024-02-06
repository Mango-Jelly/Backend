package com.mangojelly.backend.global.common;

import com.mangojelly.backend.global.error.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional(readOnly = true)
@SpringBootTest
class S3FileUploaderTest {

    @Autowired
    private S3FileUploader fileUploader;

    @DisplayName("S3 파일 업로드 테스트")
    @Nested
    class uploadFile{
        @DisplayName("성공")
        @Test
        void whenSuccess() {
            assertDoesNotThrow(()->{
                System.out.println(fileUploader.uploadFile("sample/image/sample/role/1.png","sample/image/sample/role"));
            });
        }

        @DisplayName("실패, 존재하지 않는 파일")
        @Test
        void whenFailByNotExistFile() {
            assertThrows(BusinessException.class,()->{
                fileUploader.uploadFile("sample/image/sample/role/thumbnail.png","sample/image/sample/role");
            });
        }
    }
}