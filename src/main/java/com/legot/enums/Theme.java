package com.legot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Theme {
    ALONE("ALONE", 0),
    COUPLE("COUPLE", 1),
    FRIEND("FRIEND", 2),
    TOUR("TOUR", 3),
    FAMILY("FAMILY", 4);

    private final String value;
    private final int number;
}
