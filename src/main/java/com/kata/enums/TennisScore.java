package com.kata.enums;

public enum TennisScore {
    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    GAME_WINNER("GAME_WINNER");

    private final String displayValue;

    // Constructor to associate a display value with each enum constant
    TennisScore(String displayValue) {
        this.displayValue = displayValue;
    }

    // Method to get the display value of the enum
    public String getDisplayValue() {
        return displayValue;
    }

    // Method to get the next score in the progression
    public static TennisScore getNextScore(TennisScore currentScore) {
        return switch (currentScore) {
            case ZERO -> FIFTEEN;
            case FIFTEEN -> THIRTY;
            case THIRTY -> FORTY;
            default -> GAME_WINNER;
        };
    }
}

