package com.example.data_modeling.domain.board;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/boards")
@RequiredArgsConstructor
public class BoardApi {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<?> creatingBoard(@RequestBody BoardRequestDto request) {

        Board board = boardService.creatingBoard(request);

        return ResponseEntity.ok(board);
    }

}
