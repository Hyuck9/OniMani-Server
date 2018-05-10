package me.hyuck.onimani.user.model;

import lombok.Data;

/**
 * Project : OniMani-Server
 * Created by IntelliJ IDEA
 * Developer : Lee Hyuck Gu
 * Date : 2018-05-04 오후 11:47
 */
public class UserDTO {

    @Data
    public static class Friend {
        private String userId;
        private String email;
        private String nickname;
        private String profileUrl;
        private String thumbUrl;
    }
}
