package com.mangojelly.backend.domain.movie;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    List<Movie> findTop6ByVisibleIsTrueOrderByCreateAt();
}
