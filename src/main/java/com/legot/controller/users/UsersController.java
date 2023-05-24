package com.legot.controller.users;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @GetMapping("/login")
    public ResponseEntity login() {
        return new ResponseEntity(
                "hello",
                HttpStatus.OK
        );
    }
}
