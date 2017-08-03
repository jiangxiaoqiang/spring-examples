package com.hualongdata.springstarter.data.domain;

import lombok.Data;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
@Data
public class CredentialBO {
    private Long id;

    private String email;

    private String username;

    private String phone;

    private LocalDateTime createdat;

    public static RowMapper<CredentialBO> rowMapper = (rs, rowNum) -> {
        CredentialBO bo = new CredentialBO();
        bo.setId(rs.getLong("id"));
        bo.setEmail(rs.getString("email"));
        bo.setPhone(rs.getString("phone"));
        bo.setUsername(rs.getString("username"));
        bo.setCreatedat(rs.getTimestamp("createdat").toLocalDateTime());
        return bo;
    };

}
