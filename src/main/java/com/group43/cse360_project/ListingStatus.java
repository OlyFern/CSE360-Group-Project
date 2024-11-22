package com.group43.cse360_project;

public enum ListingStatus {
    LISTED,
    PENDING;

    // Database flag creation
    public static String getListingStatusDBFlag(ListingStatus status) {
        return (status == LISTED) ? "L" : "P";
    }

    // Database flag parsing
    public static ListingStatus parseListingStatusDBFlag(String flag) {
        return (flag.compareToIgnoreCase("L") == 0) ? LISTED : PENDING;
    }
}
