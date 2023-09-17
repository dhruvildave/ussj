package org.localhost.ussj.shortner;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface ShortnerRepository extends ListCrudRepository<Shortner, String> {
    @Query(value = "select short from shortner s where long = :long", nativeQuery = true)
    String findShortByLongURL(@Param("long") String longURL);

    @Query(value = "select long from shortner s where short = :short", nativeQuery = true)
    String findLongByShortURL(@Param("short") String shortURL);
}
