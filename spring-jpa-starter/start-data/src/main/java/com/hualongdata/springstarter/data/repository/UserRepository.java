package com.hualongdata.springstarter.data.repository;

import com.hualongdata.springstarter.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yang Jing (yangbajing@gmail.com) on 2017-07-24.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
