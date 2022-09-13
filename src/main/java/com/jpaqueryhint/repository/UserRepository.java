package com.jpaqueryhint.repository;

import com.jpaqueryhint.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.hibernate.jpa.QueryHints;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.stream.Stream;

public interface UserRepository extends JpaRepository<User, Long> {

    // way 2
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

    // way 1:
    @Query(value = "select * from (\n" +
            " select id, name, ROW_NUMBER() OVER (ORDER BY name) as row_num1 FROM db_example.user) t \n" +
            "where row_num1 > :from and row_num1 < :to", nativeQuery = true)
    List<User> getAll(int from, int to);

// HQL
    @Query(value = "select u FROM User u where u.id > ?1 and u.id < ?2")
    List<User> getAllHQl1(Long from, Long to);

    @Query(value = "select u FROM User u where u.id > :from and u.id < :to")
    List<User> getAllHQl2(@Param("from") Long from, @Param("to") Long to);

}
