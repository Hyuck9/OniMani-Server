package me.hyuck.onimani.user.controller;

import lombok.extern.slf4j.Slf4j;
import me.hyuck.onimani.user.model.UserInfo;
import me.hyuck.onimani.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDateTime;


/**
 * Project : OniMani-Server
 * Created by IntelliJ IDEA
 * Developer : Lee Hyuck Gu
 * Date : 2018-04-30 오후 9:09
 */
@RestController
@Slf4j
@RequestMapping("/user/account")
public class AccountController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/allUsers")
    public Flux<UserInfo> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/login/{userId}")
    public Mono<ResponseEntity<UserInfo>> getUserById(@PathVariable("userId") String userId) {
        log.info("PathVariable Test!!");
        return userRepository.findByUserId(userId)
                .map(savedUser -> ResponseEntity.ok(savedUser))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/join")
    public Mono<UserInfo> createUser(@Valid @RequestBody UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

    @PutMapping("/modify/nickname/{userId}")
    public Mono<ResponseEntity<UserInfo>> updateUser(@PathVariable(value = "userId") String userId,
                                                      @Valid @RequestBody UserInfo user) {
        return userRepository.findById(userId)
                .flatMap(existingUser -> {
                    existingUser.setNickname(user.getNickname());
                    return userRepository.save(existingUser);
                })
                .map(updateUser -> new ResponseEntity<>(updateUser, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/leave/{userId}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable(value = "userId") String userId) {

        return userRepository.findById(userId)
                .flatMap(existingUser ->
                        userRepository.delete(existingUser)
                                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                )
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
//
//    // Tweets are Sent to the client as Server Sent Events
//    @GetMapping(value = "/stream/tweets", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<Tweet> streamAllTweets() {
//        return tweetRepository.findAll();
//    }

    /*static final String URL1 = "http://localhost:8080/test?req={req}";
    static final String URL2 = "http://localhost:8080/user/account/test/join?userinfo={userInfo}";

    WebClient client = WebClient.create();

    @GetMapping("/test/join")
    public Mono<String> getTestJoin() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("1234567890");
        userInfo.setEmail("lhg1304@gmail.com");
        userInfo.setCreatedDate(LocalDateTime.now());
        userInfo.setNickname("이혁구");

        Mono<ClientResponse> res = client.post().uri(URL2, userInfo).exchange();
        Mono<String> body = res.flatMap(clientResponse -> clientResponse.bodyToMono(String.class));
        return body;
    }

    @GetMapping("/rest")
    public Mono<String> rest(int idx) {
        Mono<ClientResponse> res = client.get().uri(URL1, idx).exchange();
        Mono<String> body = res.flatMap(clientResponse -> clientResponse.bodyToMono(String.class));
        return body;
    }

    @GetMapping("/test")
    public Mono<String> test(int req) {
        return Mono.just("test").log();
    }

    @PutMapping("/put")
    public void putTest() {
        log.debug("test");
    }*/


}
