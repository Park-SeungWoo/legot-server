package com.legot.service;

import com.legot.dao.UserRepository;
import com.legot.dto.UserSignUpRequestDto;
import com.legot.entity.Users;
import com.legot.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public ResponseEntity signUp(UserSignUpRequestDto userSignUpRequestDto) {
        userRepository.save(userSignUpRequestDto.toEntity());  // save user
        return new ResponseEntity(
                "ok",
                HttpStatus.OK
        );
    }

    @Override
    public boolean signIn(String email, String password, HttpServletResponse response) {
        Users users = userRepository.findByEmailAndPassword(email, password);
        if (users != null) {
            response.setHeader("Authorization", jwtProvider.createAccessToken(email, users.getRole()));
            return true;
        }
        else {
            response.setHeader("Authorization", "User Not Exists");
            return false;
        }
    }
}
