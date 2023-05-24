package com.legot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoginType {
    KAKAO("KAKAO", 1),
    GOOGLE("GOOGLE", 2),
    BASIC("BASIC", 0);

    private final String value;
    private final int number;
}
