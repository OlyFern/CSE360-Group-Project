package com.group43.cse360_project;

public enum UserType {
    ADMIN,
    BUYER,
    SELLER,
    RESTRICTED;

    // Database flag creation
    public static String getUserTypeDBFlag(UserType type) {
        return switch (type) {
            case ADMIN  -> "A";
            case BUYER  -> "B";
            case SELLER -> "S";
            default     -> "R"; // Restricted
        };
    }

    // Database flag parsing
    public static UserType parseUserTypeDBFlag(String flag) {
        return switch (flag) {
            case "A" -> ADMIN;
            case "B" -> BUYER;
            case "S" -> SELLER;
            default  -> RESTRICTED;
        };
    }
}
