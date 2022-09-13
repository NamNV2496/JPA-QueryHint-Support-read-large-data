package com.jpaqueryhint.controller;

import com.jpaqueryhint.domain.User;
import com.jpaqueryhint.repository.UserRepository;
import com.jpaqueryhint.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExampleController {
    private final UserService userService;
    @Autowired
    private UserRepository userRepository;

    // way 2
    @GetMapping("/get")
    public List<User> getUser() {
        log.info(String.valueOf(LocalTime.now()));
        List<User> list = userService.getAll();
        log.info(String.valueOf(LocalTime.now()));
        return list;
    }
    // start: 20:56:51.634589900
    // end: 20:56:51.650217
    // => 0.015,628
    // => 89% faster

    @GetMapping("/getAll")
    public List<User> getAll() {
        log.info(String.valueOf(LocalTime.now()));
        List<User> u = userRepository.findAll();
        log.info(String.valueOf(LocalTime.now()));
        return u;
    }

    // start: 20:53:29.335141400
    // end: 20:53:29.468150400
    // => 0.133,009,000

    // compare get one record between Stream and normal CRUD repository
    @GetMapping("/getOnce")
    @Transactional
    public User getOnce() {
        log.info(String.valueOf(LocalTime.now()));
        User u = userRepository.findOnce().collect(Collectors.toList()).get(0);
        log.info(String.valueOf(LocalTime.now()));
        return u;
    }
    // start: 21:06:38.554304400
    // end: 21:06:38.554304400

    @GetMapping("/getOne")
    public User getOne() {
        log.info(String.valueOf(LocalTime.now()));
        User u = userRepository.findOne();
        log.info(String.valueOf(LocalTime.now()));
        return u;
    }
    // start: 21:05:47.646913600
    // end: 21:05:47.646913600

    // way 1

    @GetMapping("/getway1")
    public void getWay1() {
        int maxCount = 20; // example
        log.info(String.valueOf(LocalTime.now()));
        for (int i=0; i<maxCount/10; i++) {
            userRepository.getAll(i*10, 10*(i+1));
        }
        log.info(String.valueOf(LocalTime.now()));
    }
    // start: 21:31:58.621532800
    // end: 21:31:58.777778600
    // => 0.1562458
    // slower than @GetMapping("/getAll")

    // HQL
    @GetMapping("/gethql1")
    public void getHQL1() {
        int maxCount = 20; // example
        log.info(String.valueOf(LocalTime.now()));
        for (int i=0; i<maxCount/10; i++) {
            List<User> u = userRepository.getAllHQl1((long) (i * 10), (long) (10 * (i + 1)));
            log.info(String.valueOf(u));
        }
        log.info(String.valueOf(LocalTime.now()));
    }

    @GetMapping("/gethql2")
    public void getHQL2() {
        int maxCount = 20; // example
        log.info(String.valueOf(LocalTime.now()));
        for (int i=0; i<maxCount/10; i++) {
            List<User> u = userRepository.getAllHQl1((long) (i * 10), (long) (10 * (i + 1)));
            log.info(String.valueOf(u));
        }
        log.info(String.valueOf(LocalTime.now()));
    }
}
