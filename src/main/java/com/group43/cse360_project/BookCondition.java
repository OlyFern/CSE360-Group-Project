package com.group43.cse360_project;

public enum BookCondition {
    NEW,
    USED,
    HEAVY;

    // Database flag creation
    public static String getBookConditionDBFlag(BookCondition cond) {
        return (cond == NEW) ? "N" : "U";
    }

    // Database flag parsing
    public static BookCondition parseBookConditionDBFlag(String flag) {
        return (flag.compareToIgnoreCase("N") == 0) ? NEW : USED;
    }
}
