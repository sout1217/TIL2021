package com.example.data_modeling.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User createUser(UserCreateRequest request) {
        return userRepository.save(request.toEntity());
    }

    public User findByName(String userName) {
        return userRepository.findByName(userName).orElseThrow(() -> new IllegalStateException("not existed"));
    }
}
