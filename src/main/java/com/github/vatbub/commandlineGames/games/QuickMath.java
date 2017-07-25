package com.github.vatbub.commandlineGames.games;

import com.github.vatbub.commandlineGames.Main;
import logging.FOKLogger;

import java.util.Scanner;

public class QuickMath extends Game {
    private final int increaseMaxValueEveryXCorrectAnswers = 5;
    private final int maxValueAtLaunch = 10;

    /**
     * Must return a human- and machine-readable name of the game. Must not contain spaces.
     *
     * @return a human- and machine-readable name of the game.
     */
    @Override
    public String getName() {
        return "QuickMath";
    }

    /**
     * Launches the game once.
     *
     * @param in The scanner which reads from {@code System.in}. Do not close this scanner!
     */
    @Override
    public void launch(Scanner in) {
        int correctAnswers = 0;
        int numberOfExercises = 0;

        while (true) {
            int maxNumber = (int) Math.floor(correctAnswers + (1.0 / increaseMaxValueEveryXCorrectAnswers) + maxValueAtLaunch);
            int minNumber = -maxNumber;
            int random1 = (int) (Math.random() * (maxNumber - minNumber) + minNumber);
            int random2 = (int) (Math.random() * (maxNumber - minNumber) + minNumber);
            int operation = (int) (Math.random() * 4);
            int correctAnswer;
            String operationSymbol;

            if (operation == 0) {
                // add
                operationSymbol = "+";
                correctAnswer = random1 + random2;
            } else if (operation == 1) {
                // subtract
                operationSymbol = "-";
                correctAnswer = random1 - random2;
            } else if (operation == 2) {
                // multiply
                operationSymbol = "*";
                correctAnswer = random1 * random2;
            } else if (operation == 3) {
                // divide
                operationSymbol = "/";
                correctAnswer = random1 / random2;
            } else {
                throw new IllegalStateException("Illegal operation: " + operation);
            }

            System.out.println("Please solve " + random1 + " " + operationSymbol + " " + random2 + " or type 'exit' to quit this mini game");
            String nextCommand = "";
            while (nextCommand.equals("")) {
                nextCommand = in.nextLine();
            }
            try {
                int answer = Integer.parseInt(nextCommand);
                numberOfExercises = numberOfExercises + 1;
                if (answer == correctAnswer) {
                    correctAnswers = correctAnswers + 1;
                    System.out.println("Correct!");
                } else {
                    System.out.println("Wrong!");
                    System.out.println("Correct answer: " + correctAnswer);
                }
            } catch (NumberFormatException e) {
                if (nextCommand.equalsIgnoreCase("exit")) {
                    System.out.println("You had " + correctAnswers + " out of " + numberOfExercises + " results correct!");
                    System.out.println("That's " + Math.round((correctAnswers * 100.0) / numberOfExercises) + "% correct answers!");
                    return;
                } else {
                    FOKLogger.severe(Main.class.getName(), "Unknown command: " + nextCommand);
                }
            }
        }
    }
}
