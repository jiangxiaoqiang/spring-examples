package com.hualongdata.springstarter.data.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
@Data
public class UserBO {
    private Long id;

    private String nickname;

    private LocalDateTime createdat;

    private LocalDateTime updatedat;

    private String email;

    private String username;

    private String phone;

}
