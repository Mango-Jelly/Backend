package com.mangojelly.backend.domain.script;

import com.mangojelly.backend.fixture.ScriptFixture;
import com.mangojelly.backend.global.error.exception.BusinessException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@Transactional
@ExtendWith(MockitoExtension.class)
public class ScriptServiceTest {


    @InjectMocks
    private ScriptService scriptService;

    @Mock
    private ScriptRepository scriptRepository;

    @DisplayName("Script Get 테스트")
    @Nested
    class findByIdTest{


        @Test
        @DisplayName("성공, 있을때")
        void whenSuccess() {
            // given
            Script script = ScriptFixture.SCRIPT_FIXTURE.create();
            given(scriptRepository.findById(any())).willReturn(Optional.of(script));

            // when, then
            assertDoesNotThrow(() -> {
                scriptService.findById(0);
            });
        }

        @Test
        @DisplayName("실패, 없을때")
        void whenFailByNotExist() {
            // given
            given(scriptRepository.findById(any())).willReturn(Optional.empty());

            // when, then
            assertThrows(BusinessException.class,() -> {
                scriptService.findById(0);
            });
        }

    }


}
