package com.mangojelly.backend.domain.movie;

import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.script.Script;
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

    public List<Movie> findAllMovies(){
        return movieRepository.findTop6ByVisibleIsTrueOrderByCreateAt();
    }

    @Transactional
    public Movie save(Member member, Script script, boolean visible, String address, String dpt, String party, String title){
        return movieRepository.save(movieMapper.toEntity(member, script, address, title, party, dpt, visible));
    }
}
