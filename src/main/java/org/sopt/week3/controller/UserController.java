package org.sopt.week3.controller;

import org.sopt.week3.dto.user.UserDTO;
import org.sopt.week3.dto.user.request.UserSignUpRequest;
import org.sopt.week3.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    ResponseEntity<String> signup(@ModelAttribute UserSignUpRequest userSignUpRequest) {
        userService.signUp(
                UserDTO.toUserDTO(userSignUpRequest)
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
