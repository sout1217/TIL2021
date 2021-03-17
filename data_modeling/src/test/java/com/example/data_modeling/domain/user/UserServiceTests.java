package com.example.data_modeling.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

// http://wonwoo.ml/index.php/post/1453
// https://dublin-java.tistory.com/49
@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    // 실제 객체
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void createUser() {
        // request
        UserCreateRequest request = new UserCreateRequest();
        request.setName("martin");

        // given
        User martin = User.builder()
                .id(1L)
                .name("martin")
                .build();

        given(userRepository.save(any())).willReturn(martin);

        final User user = userService.createUser(request);

        Assertions.assertEquals(user.getName(), "martin");

        verify(userRepository).save(any());

        System.out.println(user);
    }
}