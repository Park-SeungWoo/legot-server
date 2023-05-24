package com.legot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    NONE("NONE", 0),
    MALE("MALE", 1),
    FEMALE("FEMALE", 2);
    private final String value;
    private final int number;

}
