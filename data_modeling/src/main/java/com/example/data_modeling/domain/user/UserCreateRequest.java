package com.example.data_modeling.domain.user;

import lombok.Data;

@Data
public class UserCreateRequest {

    private String name;

    public User toEntity() {
        return User.builder()
                .name(name)
                .build();
    }
}
