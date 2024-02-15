DELIMITER $$
DROP PROCEDURE if exists `find_movie` $$
CREATE PROCEDURE `find_movie` (
    IN param_member_id INT,
    IN param_movie_id INT
)
BEGIN
    declare m_id integer;
    select member_id into m_id from movie where param_movie_id = movie_id;

    if (m_id = param_member_id) then
        select *
        from movie where param_movie_id = movie_id;
    else
        select *
        from movie where visible = true and movie_id=param_movie_id;
    end if;
END $$
DELIMITER ;