package com.javamentoringprogram.messenger;

public class EmailTextTemplate {

    public static final String EMAIL_SUBJECT_TEMPLATE =
            "The ${email.subject} needs review";

    public static final String EMAIL_TEXT_TEMPLATE =
            "Dear ${receiver.name}" + "\n" +
            "Kindly ask you to to take a look on the issue ${email.subject}. + \n" +
             "We would like to hear yor opinion regarding this matter. " +
             "Thank you in advance" +
              " " +
              "Kind regards" +
              "${sender.name}" +
              "${sender.position}";
}
