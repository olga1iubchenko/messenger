package com.javamentoringprogram.messenger.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExpectedExceptions {
    NULL_POINTER_EXCEPTION("NullPointerException"),
    INDEX_OUT_OF_BOUND_EXCEPTION("IndexOutOfBoundsException");

    private final String name;
}
