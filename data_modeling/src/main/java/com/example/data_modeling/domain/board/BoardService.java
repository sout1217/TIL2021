package com.example.data_modeling.domain.board;

import com.example.data_modeling.domain.user.User;
import com.example.data_modeling.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserService userService;

    @Transactional
    public Board creatingBoard(BoardRequestDto boardRequestDto) {

        User user = userService.findByName(boardRequestDto.getUserName());

        Board board = boardRequestDto.toEntity(user.getId());

        return boardRepository.save(board);
    }
}
