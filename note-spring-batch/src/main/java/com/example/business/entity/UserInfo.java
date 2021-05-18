package com.example.business.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 7633838028808016626L;

    private String id;

    private String userId;

    private String loginName;

    private String nickName;

    private String realName;

    private String phone;

    private String mail;

    private String sex;

    private LocalDate birthday;

    private Integer age;

    private String avatar;

    private String address;

    private String work;

    private String nativePlace;

    private String creditRating;

    private String remarks;

    private String flag;

    private LocalDateTime editTime;

    private String editUserId;

    private String editUserName;

    private LocalDateTime createTime;

    private String createUserId;

    private String createUserName;

}
