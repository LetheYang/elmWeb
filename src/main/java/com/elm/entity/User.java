package com.elm.entity;

import lombok.Data;

/**
 * @author akemihomurasama
 */
@Data
public class User {
    private Integer id;
    private String userId;
    private String password;
    private String userName;
    private Integer userSex;
    private String userImg;
    private Integer delTag;
}
