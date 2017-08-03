package com.hualongdata.springstarter.data.repository;

import com.hualongdata.springstarter.data.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
public interface CredentialRepository extends JpaRepository<Credential, Long>, CredentialRepositoryCustom {
}
