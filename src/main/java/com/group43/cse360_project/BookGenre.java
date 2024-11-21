package com.group43.cse360_project;

public enum BookGenre {
    NATURAL_SCIENCE,
    MATH,
    COMPUTERS,
    ENGLISH_LANGUAGE,
    OTHER;

    // Database flag creation
    public static String getBookGenreDBFlag(BookGenre genre) {
        return switch (genre) {
            case NATURAL_SCIENCE  -> "N";
            case MATH             -> "M";
            case COMPUTERS        -> "C";
            case ENGLISH_LANGUAGE -> "E";
            default               -> "O";
        };
    }

    // Database flag parsing
    public static BookGenre parseBookGenreDBFlag(String flag) {
        return switch (flag) {
            case "N" -> NATURAL_SCIENCE;
            case "M" -> MATH;
            case "C" -> COMPUTERS;
            case "E" -> ENGLISH_LANGUAGE;
            default  -> OTHER;
        };
    }
}
