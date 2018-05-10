package me.hyuck.onimani.user.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Project : OniMani-Server
 * Created by IntelliJ IDEA
 * Developer : Lee Hyuck Gu
 * Date : 2018-04-30 오후 7:41
 */
@Document(collection = "userInfo")
@Data
public class UserInfo {

//    @Id
//    private String id;

    @Indexed(unique = true)
    private String userId;

    @Indexed(unique = true)
    private String email;

    private LocalDateTime createdDate;

    private String nickname;

    private String profileUrl;

    private String thumbUrl;

    private List<UserDTO.Friend> friends;

}
