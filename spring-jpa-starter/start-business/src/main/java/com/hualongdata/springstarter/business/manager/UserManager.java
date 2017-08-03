package com.hualongdata.springstarter.business.manager;

import com.hualongdata.springstarter.common.exception.HlNotFoundException;
import com.hualongdata.springstarter.data.component.MapperComponent;
import com.hualongdata.springstarter.data.domain.CredentialBO;
import com.hualongdata.springstarter.data.domain.UserBO;
import com.hualongdata.springstarter.data.domain.UserCreateDTO;
import com.hualongdata.springstarter.data.domain.UserUpdateDTO;
import com.hualongdata.springstarter.data.entity.Credential;
import com.hualongdata.springstarter.data.entity.User;
import com.hualongdata.springstarter.data.repository.CredentialRepository;
import com.hualongdata.springstarter.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//import javax.transaction.Transactional;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
@Service
public class UserManager {
    private final MapperComponent mapperComponent;
    private final UserRepository userRepository;
    private final CredentialRepository credentialRepository;

    @Autowired
    public UserManager(MapperComponent mapperComponent, UserRepository userRepository, CredentialRepository credentialRepository) {
        this.mapperComponent = mapperComponent;
        this.userRepository = userRepository;
        this.credentialRepository = credentialRepository;
    }

    @Transactional
    public UserBO createUser(UserCreateDTO dto) {
        Credential credential = credentialRepository.save(dto.createCredential());
        User user = userRepository.save(dto.createUser(credential.getId()));

        return mapperComponent.entityToUserBO(user, credential);
    }

    @Transactional(readOnly = true)
    public UserBO findOneById(Long id) {
        User user = userRepository.findOne(id);
        CredentialBO credentialBO = credentialRepository.findOneBO(id);

        return mapperComponent.entityToUserBO(user, credentialBO);
    }

    public Page<UserBO> page(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        List<UserBO> userBOList = page.getContent().stream()
                .map(user -> {
                    CredentialBO credentialBO = credentialRepository.findOneBO(user.getId());
                    return mapperComponent.entityToUserBO(user, credentialBO);
                })
                .collect(Collectors.toList());
        return new PageImpl<>(userBOList, pageable, page.getTotalElements());
    }

    @Transactional
    public UserBO update(Long id, UserUpdateDTO dto) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new HlNotFoundException("用户不存在, 用户id: " + id);
        }

        user.setNickname(dto.getNickname());

        user = mapperComponent.updateDTOToBean(dto, user);
        user = userRepository.save(user);

        CredentialBO credentialBO = credentialRepository.findOneBO(user.getId());

        return mapperComponent.entityToUserBO(user, credentialBO);
    }

    @Transactional
    public void removeById(long id) {
        credentialRepository.delete(id);
        userRepository.delete(id);
    }
}
