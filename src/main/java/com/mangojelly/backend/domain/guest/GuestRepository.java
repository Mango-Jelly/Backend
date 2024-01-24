package com.mangojelly.backend.domain.guest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Integer> {

}
/*
save 하는 거 하나

update:
session : guest repo
get:
role : role repo - byRoleId or String
room : room repo - byAddress
 */
