package com.legot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoginType {
    BASIC("BASIC", 0),
    KAKAO("KAKAO", 1),
    GOOGLE("GOOGLE", 2);

    private final String value;
    private final int number;
}
