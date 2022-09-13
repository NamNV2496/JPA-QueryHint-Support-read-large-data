package com.jpaqueryhint.service;

import com.jpaqueryhint.domain.User;
import com.jpaqueryhint.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    // Way 2: Use Stream in JpaRepository combine with QueryHints
    @Transactional()
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        try (Stream<User> tickets = userRepository.findUser()) {
            tickets.forEach(item -> {
                userList.add(item);
                entityManager.detach(item);
                log.info(String.valueOf(item));
            });
        }
        return userList;
    }

    // Way 1: divide into smaller piece and query it

}
