package com.mangojelly.backend.domain.movie;

import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.global.error.ErrorCode;
import com.mangojelly.backend.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

//    @Transactional
//    public Movie save(Movie movie){
//
//    }
    public List<Movie> findAllMovies() {
        return movieRepository.findTop6ByVisibleIsTrueOrderByCreateAt();
    }

    public List<Movie> findAllMyMovies(Member member) {
        return movieRepository.findAllByMember(member);
    }

    public Movie findOneMovie(int memberId, int movieId) {
        return movieRepository.findMovie(memberId, movieId);
    }
}
