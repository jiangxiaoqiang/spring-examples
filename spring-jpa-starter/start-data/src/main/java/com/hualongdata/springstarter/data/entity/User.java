package com.hualongdata.springstarter.data.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Created by Yang Jing (yangbajing@gmail.com) on 2017-07-24.
 */
@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    private Long id;

    private String nickname;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdat;

    @LastModifiedDate
    private LocalDateTime updatedat;
}
