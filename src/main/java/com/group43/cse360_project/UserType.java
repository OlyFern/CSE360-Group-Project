package com.group43.cse360_project;

public enum UserType {
    ADMIN,
    BUYER,
    SELLER,
    RESTRICTED;

    public static String userTypeToString(UserType type) {
        return switch (type) {
            case ADMIN  -> "A";
            case BUYER  -> "B";
            case SELLER -> "S";
            default     -> "R"; // Restricted
        };
    }

    public static UserType parseUserType(String str) {
        return switch (str) {
            case "A" -> ADMIN;
            case "B" -> BUYER;
            case "S" -> SELLER;
            default  -> RESTRICTED;
        };
    }
}
