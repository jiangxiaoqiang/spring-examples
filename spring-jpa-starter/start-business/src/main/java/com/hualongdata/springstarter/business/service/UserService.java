package com.hualongdata.springstarter.business.service;

import com.hualongdata.springstarter.business.manager.UserManager;
import com.hualongdata.springstarter.data.domain.UserBO;
import com.hualongdata.springstarter.data.domain.UserCreateDTO;
import com.hualongdata.springstarter.data.domain.UserUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
@Service
public class UserService {
    private final UserManager userManager;

    @Autowired
    public UserService(UserManager userManager) {
        this.userManager = userManager;
    }

    public UserBO create(UserCreateDTO dto) {
        return userManager.createUser(dto);
    }

    public UserBO findOneById(Long id) {
        return userManager.findOneById(id);
    }

    public Page<UserBO> page(Pageable pageable) {
        return userManager.page(pageable);
    }

    public UserBO update(Long id, UserUpdateDTO dto) {
        return userManager.update(id, dto);
    }
}
