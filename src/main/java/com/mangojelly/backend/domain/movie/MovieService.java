package com.mangojelly.backend.domain.movie;

import com.mangojelly.backend.domain.guest.Guest;
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

    public List<Movie> findAllMovies() {
        return movieRepository.findTop6ByVisibleIsTrueOrderByCreateAt();
    }

    public List<Movie> findAllMyMovies(Member member) {
        return movieRepository.findTop6ByMemberOrderByCreateAtDesc(member);
    }

    public Movie findBy(Integer memberId, int movieId) {
        return movieRepository.findMovie(memberId, movieId);
    }

    public Movie findById(int movieId){
        return movieRepository.findById(movieId).orElseThrow(() -> BusinessException.of(ErrorCode.API_ERROR_MOVIE_NOT_EXIST));
    }
    @Transactional
    public Movie save(Member member, Script script, boolean visible, String address, String dpt, List<Guest> guests, String title){
        StringBuilder party = new StringBuilder();
        for (Guest guest : guests) {
            if (guest.getRole() != null)
                party.append(guest.getName()).append(",").append(guest.getRole().getName()).append(",");
        }

        return movieRepository.save(movieMapper.toEntity(member, script, address, title, party.toString(), dpt, visible));
    }
}
