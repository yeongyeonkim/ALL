package com.kyy.api.controller;

import com.kyy.api.repository.UserRepository;
import com.kyy.api.service.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_OK;

@Api
//@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository; // final이 붙거나 @NotNull 이 붙은 필드의 생성자를 자동 생성해주는 롬복 어노테이션

    @ApiOperation(value = "유저 리스트 조회")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_OK, message = "200 ok"),
            @ApiResponse(code = HTTP_INTERNAL_ERROR, message = "500 internal error")
    })
    @GetMapping(value = "/user")
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @ApiOperation(value = "유저 생성")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_OK, message = "200 ok"),
            @ApiResponse(code = HTTP_INTERNAL_ERROR, message = "500 internal error")
    })
    @PostMapping(value = "/user")
    public User save() {
        User user = User.builder()
                .email("dudtbd111@naver.com")
                .name("김영연")
                .build();
        return userRepository.save(user);
    }
}
