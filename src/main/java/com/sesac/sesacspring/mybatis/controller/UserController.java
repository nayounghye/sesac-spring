package com.sesac.sesacspring.mybatis.controller;

import com.sesac.sesacspring.mybatis.dto.UserDTO;
import com.sesac.sesacspring.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user") // 디폴트 url 설정
public class UserController {
    // C, R
    // 1. Table 생성 완료 (user)
    // 2. domain 만들기 (domain/user)
    // 3. mapper 만들기
    // 4. service 만들기
    // 5. controller 만들기

    @Autowired
    UserService userService;

    @GetMapping("/all") // 실제 url : /user/all
    public List<UserDTO> getUser() {
        List<UserDTO> result = userService.retrieveAll();
        return result; // 출력 : []
    }

    @GetMapping("/create") // 경로 : /user/create
    public String getUserInsert(
            @RequestParam String name,
            @RequestParam String nickname) {
        userService.createUser(name, nickname);
        return "Success";
    }

    @GetMapping("/edit")
    public String getUserEdit(
            @RequestParam int id,
            @RequestParam String nickname
            ) {
        userService.editUser(id, nickname);
        return "Edit Success";
    }

    @GetMapping("/delete")
    public String getUserDelete(
            @RequestParam int id) {
        userService.deleteUser(id);
        return "Delete Success";
    }


        }


