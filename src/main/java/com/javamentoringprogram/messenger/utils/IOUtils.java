//package com.javamentoringprogram.messenger.utils;
//
//
//import lombok.SneakyThrows;
//import org.apache.logging.log4j.core.util.FileUtils;
//
//import java.io.*;
//import java.net.URL;
//import java.nio.charset.StandardCharsets;
//
//import static java.util.Optional.ofNullable;
//
//public class IOUtils {
//
//    @SneakyThrows
//    public static File readResource(String filePath) {
//        return new File(getSystemResource(filePath).toURI());
//    }
//
//    private static URL getSystemResource(String path) {
//        return ofNullable(ClassLoader.getSystemResource(path))
//                .orElseThrow(() -> new RuntimeException("File is not found: " + path));
//    }
////
////    @SneakyThrows
////    public static String readFileAsString(String path) {
////        return FileUtils.readFileToString(readResource(path), StandardCharsets.UTF_8);
////    } }
//////
////    @SneakyThrows
////    public static InputStream getFileInputStream(String path) {
////        return new FileInputStream(getSystemResource(path).getPath());
////    }
////
////    public static boolean contentEquals(InputStream input1, InputStream input2) throws IOException {
////        if (input1 == input2) {
////            return true;
////        } else {
////            if (!(input1 instanceof BufferedInputStream)) {
////                input1 = new BufferedInputStream((InputStream)input1);
////            }
////
////            if (!(input2 instanceof BufferedInputStream)) {
////                input2 = new BufferedInputStream((InputStream)input2);
////            }
////
////            int ch2;
////            for(int ch = ((InputStream)input1).read(); -1 != ch; ch = ((InputStream)input1).read()) {
////                ch2 = ((InputStream)input2).read();
////                if (ch != ch2) {
////                    return false;
////                }
////            }
////
////            ch2 = ((InputStream)input2).read();
////            return ch2 == -1;
////        }
////    }
////
////
////
////}
////
