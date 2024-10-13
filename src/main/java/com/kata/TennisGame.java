package com.kata;

import com.kata.enums.PointWinner;
import com.kata.enums.TennisScore;

public class TennisGame {

    private TennisScore playerAScore = TennisScore.ZERO;
    private TennisScore playerBScore = TennisScore.ZERO;
    private boolean isDeuce = false;
    private String advantage = null;

    public static void main(String[] args) {
        TennisGame game = new TennisGame();
        PointWinner[] pointWinners = game.checkInput(args);
        game.play(pointWinners);
    }

    /**
     * This method validates the input arguments and returns an array of PointWinner enums
     * representing the sequence of points.
     *
     * @param args - String array input representing points won by Player A or Player B
     * @return PointWinner[] - array of PointWinner enums ('A' or 'B')
     */
    public PointWinner[] checkInput(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("No input provided. Please enter a sequence of 'A' or 'B'.");
        }

        PointWinner[] pointWinners = new PointWinner[args.length];
        for (int i = 0; i < args.length; i++) {
            if ("A".equals(args[i])) {
                pointWinners[i] = PointWinner.A;
            } else if ("B".equals(args[i])) {
                pointWinners[i] = PointWinner.B;
            } else {
                throw new IllegalArgumentException("Invalid input: " + args[i] + ". Only 'A' or 'B' are allowed.");
            }
        }

        if (pointWinners.length < 4) {
            throw new IllegalArgumentException("A tennis game must have at least 4 points.");
        }

        return pointWinners;
    }

    /**
     * This method simulates the play by iterating on the points won and updating the score.
     * After each point, it checks if the game has a winner and stops if the game ends.
     *
     * @param points - Array of PointWinner representing who won each point
     */
    public void play(PointWinner[] points) {
        for (PointWinner point : points) {
            updateScore(point);
            if (checkWinner()) {
                return; // End game if there's a winner
            }
        }
    }


    /**
     * Updates the score for Player A or Player B depending on who won the point.
     * If the score reaches 40-40, it handles deuce and advantage situations.
     *
     * @param winner - The player who won the point (either A or B)
     */
    private void updateScore(PointWinner winner) {
        if (isDeuce) {
            handleDeuceAndAdvantage(winner);
        } else {
            if (winner == PointWinner.A) {
                playerAScore = TennisScore.getNextScore(playerAScore);
            } else {
                playerBScore = TennisScore.getNextScore(playerBScore);
            }

            // Check for deuce scenario
            if (playerAScore == TennisScore.FORTY && playerBScore == TennisScore.FORTY) {
                isDeuce = true;
            }
            printScore();
        }
    }

    /**
     * Prints the current score if the game is not in deuce or advantage situations.
     */
    private void printScore() {
        if (playerAScore != TennisScore.GAME_WINNER && playerBScore != TennisScore.GAME_WINNER && !isDeuce) {
            System.out.println("Player A: " + playerAScore.getDisplayValue() + " / Player B: " + playerBScore.getDisplayValue());
        }
    }

    /**
     * Handles the game during deuce or advantage situations.
     * If there is no advantage, it assigns the advantage to the winner of the current point.
     * If there is already an advantage, it either gives the game to the player with the advantage
     * or resets the game back to deuce if the advantage is lost.
     *
     * @param winner - The player who won the point during deuce or advantage
     */
    private void handleDeuceAndAdvantage(PointWinner winner) {

        if (advantage == null) {
            System.out.println("Deuce");
            // Player win advantage
            advantage = (winner == PointWinner.A) ? "A" : "B";
            System.out.println("Advantage Player " + advantage);
        } else {
            // If the player with advantage loses, return to deuce
            if (advantage.equals("A") && winner == PointWinner.B) {
                advantage = null; // Reset advantage
            } else if (advantage.equals("B") && winner == PointWinner.A) {
                advantage = null; // Reset advantage
            } else {
                // Player with advantage wins
                System.out.println("Player " + advantage + " wins the game");
            }
        }
    }

    /**
     * Checks if there is a winner in the game by seeing if any player has reached the GAME score.
     * Prints the winner if there's one and returns true to indicate that the game is over.
     *
     * @return boolean - Returns true if there's a winner, false if not
     */
    private boolean checkWinner() {
        if (playerAScore == TennisScore.GAME_WINNER) {
            System.out.println("Player A wins the game");
            return true;
        } else if (playerBScore == TennisScore.GAME_WINNER) {
            System.out.println("Player B wins the game");
            return true;
        }

        return false;
    }
}
