package com.example.data_modeling.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

// https://pjh3749.tistory.com/241
@DataJpaTest
@Transactional
class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 생성")
    void createUser() {
        User martin = User.builder()
                .name("martin")
                .build();

        User denny = User.builder()
                .name("benny")
                .build();

        User was = User.builder()
                .name("was")
                .build();

        List<User> users = Arrays.asList(martin, denny, was);

        userRepository.saveAll(users);

        List<User> savedUsers = userRepository.findAll();

        savedUsers.forEach(System.out::println);

        assertThat(savedUsers)
                .filteredOn(savedUser -> savedUser.getName().contains("a"))
                .containsOnly(martin, was);
    }
}