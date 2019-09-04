package com.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Bill {
    ONE (1),
    FIVE(5),
    TEN(10),
    TWENTY(20);

    private int cost;
}
