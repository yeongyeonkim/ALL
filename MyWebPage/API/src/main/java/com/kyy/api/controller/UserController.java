package com.kyy.api.controller;

import com.kyy.api.repository.UserRepository;
import com.kyy.api.service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserRepository userRepository; // final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션

    @GetMapping(value = "/user")
    public List<User> findAllUser() {
        return userRepository.findAll();
    }
    @PostMapping(value = "/user")
    public User save() {
        User user = User.builder()
                .email("dudtbd111@naver.com")
                .name("김영연")
                .build();
        return userRepository.save(user);
    }
}
