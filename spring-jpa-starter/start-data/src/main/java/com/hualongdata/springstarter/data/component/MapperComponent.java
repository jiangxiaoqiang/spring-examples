package com.hualongdata.springstarter.data.component;

import com.hualongdata.springstarter.data.domain.CredentialBO;
import com.hualongdata.springstarter.data.domain.UserBO;
import com.hualongdata.springstarter.data.domain.UserUpdateDTO;
import com.hualongdata.springstarter.data.entity.Credential;
import com.hualongdata.springstarter.data.entity.User;
import ma.glasnost.orika.BoundMapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

/**
 * 所有的Bean转换功能都写在此组件中
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
@Component
public class MapperComponent {
    private final MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
            .mapNulls(false)
            .build();

    private final BoundMapperFacade<UserUpdateDTO, User> userUpdateDTOToUser = mapperFactory.getMapperFacade(UserUpdateDTO.class, User.class);

    private final BoundMapperFacade<User, UserBO> userToUserBO = mapperFactory.getMapperFacade(User.class, UserBO.class);

    private final BoundMapperFacade<Credential, UserBO> credentialToUserBO = mapperFactory.getMapperFacade(Credential.class, UserBO.class);

    private final BoundMapperFacade<CredentialBO, UserBO> credentialBOToUserBO = mapperFactory.getMapperFacade(CredentialBO.class, UserBO.class);

    public User updateDTOToBean(UserUpdateDTO source, User target) {
        return userUpdateDTOToUser.map(source, target);
    }

    public UserBO entityToUserBO(User user, CredentialBO credential) {
        UserBO bo = new UserBO();
        bo = userToUserBO.map(user, bo);
        return credentialBOToUserBO.map(credential, bo);
    }

    public UserBO entityToUserBO(User user, Credential credential) {
        UserBO bo = new UserBO();
        bo = userToUserBO.map(user, bo);
        return credentialToUserBO.map(credential, bo);
    }

}
