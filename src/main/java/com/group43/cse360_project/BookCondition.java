package com.group43.cse360_project;

public enum BookCondition {
    NEW,
    USED,
    HEAVY;

    // Database flag creation
    public static String getBookConditionDBFlag(BookCondition cond) {
        return switch (cond) {
            case NEW   -> "N";
            case USED  -> "U";
            case HEAVY -> "H";
        };
    }

    // Database flag parsing
    public static BookCondition parseBookConditionDBFlag(String flag) {
        return switch (flag) {
            case "N" -> NEW;
            case "U" -> USED;
            default  -> HEAVY;
        };
    }

    public static String bookConditionAsString(BookCondition cond) {
        return switch (cond) {
            case NEW  -> "Like New";
            case USED -> "Lightly Used";
            default   -> "Heavily Used";
        };
    }
}
