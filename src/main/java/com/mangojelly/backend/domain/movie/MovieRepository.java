package com.mangojelly.backend.domain.movie;

import com.mangojelly.backend.domain.member.Member;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    List<Movie> findTop6ByVisibleIsTrueOrderByCreateAt();
    List<Movie> findAllByMember(Member member);
    @Procedure("find_movie")
    Movie findMovie(int memberId, int movieId);
}
