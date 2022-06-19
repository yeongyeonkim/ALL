package com.kyy.api.controller;

import com.kyy.api.service.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class UserController {
    @RequestMapping("/")
    public String user(){
        return "user";
    }
    @RequestMapping("/{id}")
    public User findById(@PathVariable long id, HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setId(id);
        user.setName("YeongYeon");
        System.out.println(id);
        return user;
    }
    @PostMapping("/ip")
    public ResponseEntity<String> ip (HttpServletRequest request){
        return ResponseEntity.ok(request.getRemoteAddr());
    }
}
