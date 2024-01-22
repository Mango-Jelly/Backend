package com.mangojelly.backend.applicatoin.facade;

import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.member.MemberService;
import com.mangojelly.backend.domain.scene.Scene;
import com.mangojelly.backend.domain.scene.SceneService;
import com.mangojelly.backend.domain.sceneMovie.SceneMovieService;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MovieFacade {
    private final MemberService memberService;
    private final SceneService sceneService;
    private final SceneMovieService sceneMovieService;

    @Transactional
    public void saveSceneMovie(int memberId, int sceneId, MultipartFile movieFile) {
        Member member = memberService.findById(memberId);
        Scene scene = sceneService.findById(sceneId);
        if( member.getRoom().getScript().getId() != scene.getScript().getId()){
            throw BusinessException.of(ErrorCode.API_ERROR_MOVIE_NOT_EQUAL_SCRIPT);
        }

        sceneMovieService.save(member.getRoom(), scene, movieFile);
    }
}
