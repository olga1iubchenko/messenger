package com.javamentoringprogram.messenger.helpers.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MockInOut {

    private PrintStream orig;
    private InputStream irig;
    private ByteArrayOutputStream os;
    private ByteArrayInputStream is;

    private final static Charset charset;

    static {
        // Our source and tests files are UTF-8, so we assert against UTF-8 strings
        // and don't want MockInOut to convert to the native charset.
        if (Charset.availableCharsets().containsKey("UTF-8")) {
            charset = Charset.forName("UTF-8");
        } else {
            charset = Charset.defaultCharset();
        }
    }

    public MockInOut(String input) {
        orig = System.out;
        irig = System.in;

        os = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(os, false, charset.name()));
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }

        is = new ByteArrayInputStream(input.getBytes());
        System.setIn(is);
    }

    /**
     * You can use this if you want to check how much of the input was read.
     */
    public ByteArrayInputStream getInputStream() {
        return is;
    }

    /**
     * Returns everything written to System.out since this {@code MockInOut} was
     * constructed. Can't be called on a closed {@code MockInOut}
     */
    public String getOutput() {
        if (os != null) {
            try {
                return os.toString(charset.name()).replace("\r\n", "\n");
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            throw new Error("getOutput on closed MockInOut!");
        }
    }

    public List<String> getFilteredOutput(){
        List<String> listOfAttributes = new ArrayList<>();
        try (Stream<String> stream = Arrays.stream(getInputStream().toString().split(" "))) {
            {
                stream
                        .filter(line -> line != null && line.length() > 0)
                        .map(line -> line.split("\\s+"))
                        .forEach(word -> {
                            for (String str : word)
                                if (str.contains("#{")) {
                                    String filteredWord = str.substring(str.indexOf("{"), str.indexOf("}")).replace("{", "");
                                    listOfAttributes.add(filteredWord);
                                }
                        });
            }
        }
        return listOfAttributes;
    }

    /**
     * Restores System.in and System.out
     */
    public void close() {
        os = null;
        is = null;
        System.setOut(orig);
        System.setIn(irig);
    }
}