package com.example.data_modeling.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserApi.class)
class UserApiTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    @Order(1)
    @DisplayName("유저 생성하기")
    void createUser() throws Exception {

        User mockUser = User.builder()
                .name("martin")
                .build();

        UserCreateRequest request = new UserCreateRequest();
        request.setName("martin");

        // given
        given(userService.createUser(request)).willReturn(mockUser);


        // when
        ResultActions actions = mvc.perform(post("/api/v1/users")
                .content("{\"name\":\"martin\"}")
                .contentType(MediaType.APPLICATION_JSON));


        // then
        actions
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/api/v1/users/1"))
                .andExpect(content().string("martin"))
//                .andExpect(jsonPath("$.name").value("martin"))
        ;

    }

}