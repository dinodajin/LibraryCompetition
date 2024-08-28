package com.example.librarycompetition.utils;

public class MemberUtils {

    public static String calcWarning(Integer damageCount, Integer loanCount) {
        String memberWarning;
        Integer label = damageCount/loanCount;

        switch (label) {
            case(0) -> memberWarning = "정상";
            case(1) -> memberWarning = "경고";
            default -> memberWarning = "위험";
        }

        return memberWarning;
    }

}
