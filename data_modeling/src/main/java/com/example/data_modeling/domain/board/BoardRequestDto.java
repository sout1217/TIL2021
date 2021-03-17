package com.example.data_modeling.domain.board;

import lombok.Data;

@Data
public class BoardRequestDto {

    private String userName;
    private String title;

    public Board toEntity(Long userId) {
        return Board.builder()
                .user_id(userId)
                .title(title)
                .build();
    }
}
