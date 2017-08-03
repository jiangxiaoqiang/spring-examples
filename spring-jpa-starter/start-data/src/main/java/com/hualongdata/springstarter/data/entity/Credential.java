package com.hualongdata.springstarter.data.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Yang Jing (yangbajing@gmail.com) on 2017-07-25.
 */
@Entity
@Table(name = "credential")
@Data
public class Credential {
    @Id
    @SequenceGenerator(name = "credential_id_seq", sequenceName = "credential_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credential_id_seq")
    @Column(updatable = false)
    private Long id;

    private String email;

    private String username;

    private String phone;

    private byte[] salt;

    private byte[] saltpassword;

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdat;
}
