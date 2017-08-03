package com.hualongdata.springstarter.data.repository;

import com.hualongdata.springstarter.data.domain.CredentialBO;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
public interface CredentialRepositoryCustom {
    CredentialBO findOneBO(Long id);
}
