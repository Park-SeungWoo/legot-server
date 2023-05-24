package com.legot.dto;


import com.legot.entity.Users;
import com.legot.enums.Gender;
import com.legot.enums.LoginType;
import com.legot.enums.UserRole;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@ToString
public class UserSignUpRequestDto {
    private String email;
    private String password;
    private String name;
    private Gender gender;
    private Timestamp birth;
    private String phone;
    private LoginType loginType;
    public Users toEntity(){
        return Users.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .gender(this.gender)
                .birth(this.birth)
                .phone(this.phone)
                .loginType(this.loginType)
                .role(UserRole.USER)
                .build();
    }
}
