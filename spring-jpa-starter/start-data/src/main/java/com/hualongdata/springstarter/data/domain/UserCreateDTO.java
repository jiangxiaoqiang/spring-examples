package com.hualongdata.springstarter.data.domain;

import com.hualongdata.springstarter.common.domain.SaltPassword;
import com.hualongdata.springstarter.data.entity.Credential;
import com.hualongdata.springstarter.data.entity.User;
import com.hualongdata.springstarter.util.SecurityUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
@Data
@ApiModel(value = "创建用户DTO")
public class UserCreateDTO {
    @ApiModelProperty(value = "账号，具体账号类型见：accountType字段", required = true)
    @NotBlank
    private String account;

    @ApiModelProperty(required = true, allowableValues = "EMAIL, USERNAME, PHONE")
    @NotBlank
    @Pattern(regexp = "EMAIL|USERNAME|PHONE")
    private String accountType;

    @ApiModelProperty(required = true, value = "[6, 48]字符")
    @NotBlank
    @Length(min = 6, max = 48)
    private String password;

    //    @Length(min = 2, max = 48)
    @ApiModelProperty(value = "昵称")
    private String nickname;

    public Credential createCredential() {
        SaltPassword saltPassword = SecurityUtils.saltPassword(password);
        Credential credential = new Credential();
        switch (accountType) {
            case AccountType.EMAIL:
                credential.setEmail(account);
                break;
            case AccountType.PHONE:
                credential.setPhone(account);
                break;
            default: // AccountType.USERNAME
                credential.setUsername(account);
        }
        credential.setSalt(saltPassword.getSalt());
        credential.setSaltpassword(saltPassword.getSaltPassword());
        return credential;
    }

    public User createUser(Long id) {
        User user = new User();
        user.setId(id);
        user.setNickname(nickname);
        return user;
    }
}
