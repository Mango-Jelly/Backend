package com.mangojelly.backend.domain.movie;

import com.mangojelly.backend.domain.member.Member;
import com.mangojelly.backend.domain.script.Script;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public Movie toEntity(Member member, Script script, String address, String title, String party, String dpt, boolean visible){
        return Movie.builder()
                .member(member)
                .script(script)
                .address(address)
                .title(title)
                .party(party)
                .dpt(dpt)
                .visible(visible)
                .build();
    }

}
