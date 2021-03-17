package com.example.data_modeling.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserCreateRequest request) throws URISyntaxException {

        User user = userService.createUser(request);

        URI uri = new URI(String.format("/api/v1/users/%d", user.getId()));

        return ResponseEntity.created(uri).body(user.getName());
    }

}
