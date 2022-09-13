package com.jpaqueryhint.repository;

import com.jpaqueryhint.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.hibernate.jpa.QueryHints;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.stream.Stream;

public interface UserRepository extends JpaRepository<User, Long> {

    @org.springframework.data.jpa.repository.QueryHints(value = {
            @QueryHint(name = QueryHints.HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE), // config to read only 1 row in one times
            @QueryHint(name = QueryHints.HINT_CACHEABLE, value = "false"), // config to don't use cache
            //@QueryHint(name = QueryHints.HINT_READONLY, value = "true") // read only
    })
    @Query(value = "SELECT * FROM db_example.user", nativeQuery = true)
    Stream<User> findUser();


    @Query(value = "SELECT * FROM db_example.user", nativeQuery = true)
    List<User> findAll();


    @Query(value = "SELECT * FROM db_example.user", nativeQuery = true)
    Stream<User> findOnce();

    @Query(value = "SELECT * FROM db_example.user limit 1", nativeQuery = true)
    User findOne();
}
