package com.javamentoringprogram.messenger.helpers.io;

import java.io.Console;

public class ConsoleSrc {
    public static String readFromConsole() {
        String str = null;
        try {
            Console con = System.console();
            System.out.println("The console object is: " + con);
            str = con.readLine();
            System.out.println("String is : " + str);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }
}
