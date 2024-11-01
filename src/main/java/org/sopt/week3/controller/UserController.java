package org.sopt.week3.controller;

import org.sopt.week3.dto.user.UserDTO;
import org.sopt.week3.dto.user.request.UserLoginRequest;
import org.sopt.week3.dto.user.request.UserSignUpRequest;
import org.sopt.week3.dto.user.response.UserLoginResponse;
import org.sopt.week3.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    ResponseEntity<String> signup(@RequestBody UserSignUpRequest userSignUpRequest) {
        userService.signUp(
                UserDTO.toUserDTO(userSignUpRequest)
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        UserDTO userDTO = userService.login(UserDTO.toUserDTO(userLoginRequest));
        UserLoginResponse userLoginResponse = UserLoginResponse.toUserLoginResponse(userDTO);

        return ResponseEntity.ok(userLoginResponse);
    }
}
