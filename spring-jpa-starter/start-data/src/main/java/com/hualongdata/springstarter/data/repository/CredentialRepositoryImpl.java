package com.hualongdata.springstarter.data.repository;

import com.hualongdata.springstarter.data.domain.CredentialBO;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
public class CredentialRepositoryImpl implements CredentialRepositoryCustom {
    private final JdbcTemplate jdbcTemplate;

    public CredentialRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public CredentialBO findOneBO(Long id) {
        String sql = "SELECT id, email, username, phone, createdat FROM credential WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, CredentialBO.rowMapper);
    }

}
