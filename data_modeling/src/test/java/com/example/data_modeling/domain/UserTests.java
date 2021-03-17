package com.example.data_modeling.domain;

import com.example.data_modeling.domain.board.Board;
import com.example.data_modeling.domain.board.BoardRepository;
import com.example.data_modeling.domain.team.Team;
import com.example.data_modeling.domain.team.TeamRepository;
import com.example.data_modeling.domain.user.User;
import com.example.data_modeling.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private TeamRepository teamRepository;


    @Test
    void createUser() {

        User martin = User.builder()
                .name("martin")
                .build();

        User savedUser = userRepository.save(martin);

        System.out.println(savedUser);
    }

    // 유저 게시판 추가하기
    @Test
    void addBoard() {
        User martin = User.builder()
                .name("martin")
                .build();

        User savedUser = userRepository.save(martin);

        Board board = Board.builder()
                .title("제목없음 1")
                .user_id(savedUser.getId())
                .build();

        Board savedBoard = boardRepository.save(board);

        System.out.println(savedBoard);
    }

    // 유저가 팀을 생성 후 TeamBoard 작성하기
    @Test
    void addTeamBoard() {

        User martin = User.builder()
                .name("martin")
                .build();

        User savedUser = userRepository.save(martin);

        Team roseClass = Team.builder()
                .name("장미반")
                .user_id(savedUser.getId())
                .build();

        Team savedTeam = teamRepository.save(roseClass);


        Board board = Board.builder()
                .title("제목없음 1")
                .user_id(savedUser.getId())
                .team_id(savedTeam.getId())
                .build();

        Board savedBoard = boardRepository.save(board);

        System.out.println(savedBoard);
    }
}