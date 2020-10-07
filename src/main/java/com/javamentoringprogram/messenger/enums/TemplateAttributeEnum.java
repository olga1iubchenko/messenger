package com.javamentoringprogram.messenger.enums;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TemplateAttributeEnum {
        EMAIL_SUBJECT("subject"),
        RECEIVER_NAME("receiver"),
        SENDER_POSITION("sender_position"),
        SENDER_NAME("sender_name");

        private final String name;
}




