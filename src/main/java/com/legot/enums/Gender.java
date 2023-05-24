package com.legot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MALE("MALE", 1),
    FEMALE("FEMALE", 2),
    NONE("NONE", 0);
    private final String value;
    private final int number;

}
