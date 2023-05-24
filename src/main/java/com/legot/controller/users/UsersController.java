package com.legot.controller.users;

import com.legot.dto.UserSignInRequestDtd;
import com.legot.dto.UserSignUpRequestDto;
import com.legot.service.UserService;
import com.legot.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity signup(@RequestBody UserSignUpRequestDto userSignUpRequestDto) {
        return userService.signUp(userSignUpRequestDto);
    }

    @PostMapping("/signin")  // ssh 적용 후 get으로 변경 예정
    public ResponseEntity signin(@RequestBody UserSignInRequestDtd userSignInRequestDtd, HttpServletResponse response) {
        if(userService.signIn(userSignInRequestDtd.getEmail(), userSignInRequestDtd.getPassword(), response))
            return new ResponseEntity(
                    "Success",
                    HttpStatus.OK
            );
        else
            return new ResponseEntity(
                    "Failed",
                    HttpStatus.UNAUTHORIZED
            );
    }
}
