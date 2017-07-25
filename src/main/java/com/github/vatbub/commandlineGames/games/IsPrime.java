package com.github.vatbub.commandlineGames.games;

import com.github.vatbub.commandlineGames.Main;
import logging.FOKLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IsPrime extends Game {
    @SuppressWarnings("FieldCanBeLocal")
    private final int increaseMaxValueEveryXCorrectAnswers = 1;
    @SuppressWarnings("FieldCanBeLocal")
    private final int maxValueAtLaunch = 50;

    /**
     * Must return a human- and machine-readable name of the game. Must not contain spaces.
     *
     * @return a human- and machine-readable name of the game.
     */
    @Override
    public String getName() {
        return "IsPrime";
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
        List<Integer> usedNumbers = new ArrayList<>();

        while (true) {
            int maxNumber = (int) Math.floor(correctAnswers + (1.0 / increaseMaxValueEveryXCorrectAnswers) + maxValueAtLaunch);
            int minNumber = 2;
            int random1;

            do {
                random1 = (int) (Math.random() * (maxNumber - minNumber) + minNumber);
            } while (usedNumbers.contains(random1));
            usedNumbers.add(random1);

            System.out.println("Is " + random1 + " a prime number? Type 'y', 'yes', 'n or 'no' for an answer or 'exit' to quit this mini game");
            String nextCommand = "";
            while (nextCommand.equals("")) {
                nextCommand = in.nextLine();
            }

            boolean answer = false;
            boolean noError = false;

            if (nextCommand.equalsIgnoreCase("exit")) {
                System.out.println("You had " + correctAnswers + " out of " + numberOfExercises + " results correct!");
                System.out.println("That's " + Math.round((correctAnswers * 100.0) / numberOfExercises) + "% correct answers!");
                return;
            } else if (nextCommand.equalsIgnoreCase("y") || nextCommand.equalsIgnoreCase("yes")) {
                answer = true;
                noError = true;
            } else if (nextCommand.equalsIgnoreCase("n") || nextCommand.equalsIgnoreCase("no")) {
                noError = true;
            } else {
                FOKLogger.severe(Main.class.getName(), "Unknown command: " + nextCommand);
            }

            if (noError) {
                numberOfExercises++;
                if (answer == isPrime(random1)) {
                    System.out.println("Correct!");
                    correctAnswers++;
                } else {
                    System.out.println("Wrong!");
                }
            }
        }
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        //check if n is a multiple of 2
        if (number % 2 == 0) return false;
        //if not, then just check the odds
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0)
                return false;
        }
        return true;
    }
}
