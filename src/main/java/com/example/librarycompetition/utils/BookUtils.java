package com.example.librarycompetition.utils;

public class BookUtils {

    public static String calcWarning(Integer bookDamage) {
        String bookWarning;
        Integer label = bookDamage/33;

        switch (label) {
            case(0) -> {bookWarning = "정상";}
            case(1) -> {bookWarning = "경고";}
            default -> {bookWarning = "위험";}
        }

        return bookWarning;
    }

}
