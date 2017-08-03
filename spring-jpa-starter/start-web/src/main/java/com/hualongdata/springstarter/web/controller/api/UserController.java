package com.hualongdata.springstarter.web.controller.api;

import com.hualongdata.springstarter.business.service.UserService;
import com.hualongdata.springstarter.data.domain.UserBO;
import com.hualongdata.springstarter.data.domain.UserCreateDTO;
import com.hualongdata.springstarter.data.domain.UserUpdateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-25.
 */
@RestController
@RequestMapping(path = "/api/user")
@Api(value = "用户API")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "创建用户", code = 201)
    @PostMapping
    public ResponseEntity<UserBO> create(@Valid @RequestBody UserCreateDTO dto) {
        return new ResponseEntity<>(userService.create(dto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "根据ID获取用户")
    @GetMapping(path = "{id}")
    public UserBO findOneById(@PathVariable Long id) {
        return userService.findOneById(id);
    }

    @GetMapping(path = "page")
    @ApiOperation(value = "分页获取用户信息", notes = "排序字段格式示例：sort=firstname&sort=lastname,asc")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码，从0开始", defaultValue = "0", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页返回记录条数", defaultValue = "20", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "sort", value = "排序字段，格式：property,property(,ASC|DESC)。", dataType = "int", paramType = "query")
    })
    public Page<UserBO> page(Pageable pageable) {
        return userService.page(pageable);
    }

    @PutMapping(path = "{id}")
    @ApiOperation(value = "修改用户")
    public UserBO update(@PathVariable Long id, @RequestBody UserUpdateDTO dto) {
        return userService.update(id, dto);
    }
}
