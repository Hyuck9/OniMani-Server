package me.hyuck.onimani.user.repository;

import me.hyuck.onimani.user.model.UserInfo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * Project : OniMani-Server
 * Created by IntelliJ IDEA
 * Developer : Lee Hyuck Gu
 * Date : 2018-05-01 오후 4:43
 */
public interface UserRepository extends ReactiveMongoRepository<UserInfo, String> {

    Mono<UserInfo> findByUserId(String s);
}
