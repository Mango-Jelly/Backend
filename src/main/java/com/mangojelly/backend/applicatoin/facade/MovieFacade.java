package com.mangojelly.backend.applicatoin.facade;

import com.mangojelly.backend.applicatoin.dto.response.GetAllMovieResponse;
import com.mangojelly.backend.applicatoin.dto.response.MovieDetailResponse;
import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.member.MemberService;
import com.mangojelly.backend.domain.movie.Movie;
import com.mangojelly.backend.domain.movie.MovieService;
import com.mangojelly.backend.domain.scene.Scene;
import com.mangojelly.backend.domain.scene.SceneService;
import com.mangojelly.backend.domain.sceneMovie.SceneMovieService;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MovieFacade {
    private final MemberService memberService;
    private final SceneService sceneService;
    private final SceneMovieService sceneMovieService;
    private final MovieService movieService;

    @Transactional
    public void saveSceneMovie(int memberId, int sceneId, MultipartFile movieFile) {
        Member member = memberService.findById(memberId);
        Scene scene = sceneService.findById(sceneId);
        if (member.getRoom().getScript().getId() != scene.getScript().getId()) {
            throw BusinessException.of(ErrorCode.API_ERROR_MOVIE_NOT_EQUAL_SCRIPT);
        }

        sceneMovieService.save(member.getRoom(), scene, movieFile);
    }

    /**
     * 공개 영상 리스트 GET 메서드
     * @return 영상 리스트
     */
    public GetAllMovieResponse findAll(){
        List<Movie> movies = movieService.findAllMovies();
        return GetAllMovieResponse.of(movies);
    }

    /**
     * 회원의 영상 리스트 GET 메서드
     * @param memberId
     * @return 회원의 영상리스트
     */
    public GetAllMovieResponse findAllByMemberId(int memberId){
        Member member = memberService.findById(memberId);
        List<Movie> movies = movieService.findAllMyMovies(member);
        return GetAllMovieResponse.of(movies);
    }

    @Transactional
    public MovieDetailResponse findByMemberIdAndMovieId(Integer memberId, int movieId){
        Movie movie = movieService.findBy(memberId,movieId);
        return MovieDetailResponse.of(movie);
    }
}
