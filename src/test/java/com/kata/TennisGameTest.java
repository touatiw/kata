package com.kata;

import com.kata.enums.PointWinner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TennisGameTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    void testMainWithInputABABAA() {
        // Given the input sequence "ABABAA"
        String[] args = {"A", "B", "A", "B", "A", "A"};

        // When
        TennisGame.main(args);

        // Then
        String expectedOutput =
                "Player A: 15 / Player B: 0\n" +
                        "Player A: 15 / Player B: 15\n" +
                        "Player A: 30 / Player B: 15\n" +
                        "Player A: 30 / Player B: 30\n" +
                        "Player A: 40 / Player B: 30\n" +
                        "Player A wins the game\n";

        assertEquals(expectedOutput, getActualOutput());
    }

    @Test
    void testMainWithInputBBBB() {
        // Given the input sequence "BBBB"
        String[] args = {"B", "B", "B", "B"};

        // When
        TennisGame.main(args);

        // Then
        String expectedOutput =
                "Player A: 0 / Player B: 15\n" +
                        "Player A: 0 / Player B: 30\n" +
                        "Player A: 0 / Player B: 40\n" +
                        "Player B wins the game\n";

        assertEquals(expectedOutput, getActualOutput());
    }

    @Test
    void testMainWithInputABABABBAAA() {
        // Given the input sequence "ABABABBAAA"
        String[] args = {"A", "B", "A", "B", "A", "B", "B", "A", "A", "A"};

        // When
        TennisGame.main(args);

        // Then
        String expectedOutput =
                "Player A: 15 / Player B: 0\n" +
                        "Player A: 15 / Player B: 15\n" +
                        "Player A: 30 / Player B: 15\n" +
                        "Player A: 30 / Player B: 30\n" +
                        "Player A: 40 / Player B: 30\n" +
                        "Deuce\n" +
                        "Advantage Player B\n" +
                        "Deuce\n" +
                        "Advantage Player A\n" +
                        "Player A wins the game\n";

        assertEquals(expectedOutput, getActualOutput());
    }

    @Test
    void testMainWithInputAAABBBBAABBB() {
        // Given the input sequence "AAABBBBAABBB"
        String[] args = {"A", "A", "A", "B", "B", "B", "B", "A", "A", "B", "B", "B"};

        // When
        TennisGame.main(args);

        // Then
        String expectedOutput =
                "Player A: 15 / Player B: 0\n" +
                        "Player A: 30 / Player B: 0\n" +
                        "Player A: 40 / Player B: 0\n" +
                        "Player A: 40 / Player B: 15\n" +
                        "Player A: 40 / Player B: 30\n" +
                        "Deuce\n" +
                        "Advantage Player B\n" +
                        "Deuce\n" +
                        "Advantage Player A\n" +
                        "Deuce\n" +
                        "Advantage Player B\n" +
                        "Player B wins the game\n";

        assertEquals(expectedOutput, getActualOutput());
    }

    @Test
    void testCheckInputValid() {
        TennisGame game = new TennisGame();

        // Valid input: A and B
        String[] args = {"A", "B", "A", "B", "A"};
        PointWinner[] expected = {PointWinner.A, PointWinner.B, PointWinner.A, PointWinner.B, PointWinner.A};

        // Run the checkInput method and assert that the result matches the expected array
        PointWinner[] result = game.checkInput(args);
        assertArrayEquals(expected, result);
    }

    @Test
    void testCheckInputInvalidCharacter() {
        TennisGame game = new TennisGame();

        // Invalid input: contains "X"
        String[] args = {"A", "X", "B"};

        // Assert that an IllegalArgumentException is thrown with the correct message
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            game.checkInput(args);
        });

        assertEquals("Invalid input: X. Only 'A' or 'B' are allowed.", exception.getMessage());
    }

    @Test
    void testCheckInputEmptyArray() {
        TennisGame game = new TennisGame();

        // Empty input: no arguments
        String[] args = {};

        // Assert that an IllegalArgumentException is thrown with the correct message
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            game.checkInput(args);
        });

        assertEquals("No input provided. Please enter a sequence of 'A' or 'B'.", exception.getMessage());
    }

    @Test
    void testCheckInputLessThanFourPoints() {
        TennisGame game = new TennisGame();

        // Input with less than 4 points
        String[] args = {"A", "B", "A"};

        // Assert that an IllegalArgumentException is thrown with the correct message
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            game.checkInput(args);
        });

        assertEquals("A tennis game must have at least 4 points.", exception.getMessage());
    }

    /**
     * Windows: System.lineSeparator() returns "\r\n"
     * Linux: System.lineSeparator() returns "\n"
     */
    private String getActualOutput() {
        return outputStream.toString().replace(System.lineSeparator(), "\n");
    }
}
