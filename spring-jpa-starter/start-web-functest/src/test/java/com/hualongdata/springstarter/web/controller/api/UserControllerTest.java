package com.hualongdata.springstarter.web.controller.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hualongdata.springstarter.business.manager.UserManager;
import com.hualongdata.springstarter.data.domain.UserBO;
import com.hualongdata.springstarter.data.domain.UserCreateDTO;
import com.hualongdata.springstarter.data.domain.UserUpdateDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * 控制器测试类，应覆盖完所有API方法
 * Created by yangbajing(yangbajing@gmail.com) on 2017-07-26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT/*, classes = SpringstarterApplication.class*/)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserManager userManager;

    private UserBO testUser;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
//        tokenCode = Signin.testSignin(mockMvc, "admin@test.com", "123456");
    }

    @Test
    public void create() throws Exception {
        UserCreateDTO dto = new UserCreateDTO();
        dto.setAccount("test@springstarter.com");
        dto.setAccountType("EMAIL");
        dto.setPassword("yang.xunjing");
        String content = objectMapper.writeValueAsString(dto);
        MvcResult result = mockMvc.perform(
                post("/api/user")
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .contentType("application/json")
                        .content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andDo(print())
                .andReturn();
        testUser = objectMapper.readValue(result.getResponse().getContentAsString(), UserBO.class);
    }

    @Test
    public void page() throws Exception {
        mockMvc.perform(get("/api/user/page"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages").isNumber())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void update() throws Exception {
        Long userId = testUser != null ? testUser.getId() : 1L;
        UserUpdateDTO dto = new UserUpdateDTO();
        dto.setNickname("羊八井");
        String content = objectMapper.writeValueAsString(dto);
        mockMvc.perform(
                put("/api/user/" + userId)
                        .characterEncoding(StandardCharsets.UTF_8.name())
                        .contentType("application/json")
                        .content(content))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andReturn();
    }

    @Test
    public void findOneById() throws Exception {
        Long userId = testUser != null ? testUser.getId() : 1L;
        mockMvc.perform(get("/api/user/" + userId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nickname").value("羊八井"))
                .andReturn();
    }

    /**
     * 此方法在控制器中关不存在，用于清理测试数据
     */
    @Test
    public void cleanup() {
        if (testUser != null) {
            userManager.removeById(testUser.getId());
        }
    }

}
