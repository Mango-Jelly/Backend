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
import com.mangojelly.backend.global.common.FileDownLoader;
import com.mangojelly.backend.global.common.PythonRunComponent;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
@Slf4j
public class MovieFacade {
    private final MemberService memberService;
    private final SceneService sceneService;
    private final SceneMovieService sceneMovieService;
    private final MovieService movieService;
    private final FileDownLoader fileDownLoader;
    private final PythonRunComponent pythonRunComponent;

    @Transactional
    public void saveSceneMovie(int memberId, int sceneId, MultipartFile movieFile, MultipartFile audioFile) {
        Member member = memberService.findById(memberId);
        Scene scene = sceneService.findById(sceneId);
        if (member.getRoom().getScript().getId() != scene.getScript().getId()) {
            throw BusinessException.of(ErrorCode.API_ERROR_MOVIE_NOT_EQUAL_SCRIPT);
        }
        fileDownLoader.loadFile(movieFile,"util/videos/");
        fileDownLoader.loadFile(audioFile,"util/videos/");

        List<String> commandTest = new ArrayList<>();
        commandTest.add("VideoConverter.py");
        commandTest.add("./videos/" + movieFile.getOriginalFilename());
        commandTest.add("./videos/" + audioFile.getOriginalFilename());

        commandTest.add("--delete");
        try {
            pythonRunComponent.runPy(commandTest);
        }catch (Exception e){
            e.printStackTrace();
            throw BusinessException.of(ErrorCode.API_ERROR_MOVIE_CREATE_FAIL);
        }
        String editedScneMovieName = movieFile.getOriginalFilename();
        String substring = editedScneMovieName.substring(0, editedScneMovieName.length() - ".mp4".length() - 1);
        sceneMovieService.save(member.getRoom(), scene, "util/videos/"+ substring +".mp4");
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
