package me.hyuck.onimani.user;

import me.hyuck.onimani.user.model.UserInfo;
import me.hyuck.onimani.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * Project : OniMani-Server
 * Created by IntelliJ IDEA
 * Developer : Lee Hyuck Gu
 * Date : 2018-05-01 오후 5:01
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void crud() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("1234567890");
        userInfo.setEmail("lhg1304@gmail.com");
        userInfo.setCreatedDate(LocalDateTime.now());
        userInfo.setNickname("이혁구");

        userRepository.save(userInfo).block();
    }

}