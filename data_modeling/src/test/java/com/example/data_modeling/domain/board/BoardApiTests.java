package com.example.data_modeling.domain.board;

import com.example.data_modeling.domain.user.User;
import com.example.data_modeling.domain.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BoardApi.class)
class BoardApiTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private BoardService boardService;

    @MockBean
    private UserService userService;

    @Test
    @Order(1)
    @DisplayName("보드 생성")
    void createBoard() throws Exception {

        BoardRequestDto boardRequestDto = new BoardRequestDto();
        boardRequestDto.setTitle("First Post");

        User martin = User.builder()
                .id(1L)
                .name("martin")
                .build();

        Board mockBoard = Board.builder()
                .title("First Post")
                .user_id(martin.getId())
                .build();

        // given
        given(boardService.creatingBoard(boardRequestDto)).willReturn(mockBoard);
        given(userService.findByName("martin")).willReturn(martin);


        // when
        ResultActions actions = mvc.perform(post("/api/v1/boards")
                .content(mapper.writeValueAsString(boardRequestDto))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user_id").value(1))
                .andExpect(jsonPath("$.title").value("First Post"))
        ;

    }
}