package com.legot.service;

import com.legot.dto.UserSignUpRequestDto;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
    public ResponseEntity signUp(UserSignUpRequestDto userSignUpRequestDto);
    public boolean signIn(String email, String password, HttpServletResponse response);
}
