package com.group43.cse360_project;

public enum BookCondition {
    NEW,
    USED,
    HEAVY;

    // Database flag creation

    @Override
    public String toString() {
        return switch (this.name()) {
            case "NEW"   -> "New";
            case "USED"  -> "Used";
            case "HEAVY" -> "Heavily Used";
            default -> throw new IllegalStateException("Unexpected value: " + this.name());
        };
    }

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

    // Returns a string representation of the book condition
    public static String bookConditionAsString(BookCondition cond) {
        return switch (cond) {
            case NEW  -> "Like New";
            case USED -> "Lightly Used";
            default   -> "Heavily Used";
        };
    }
}
