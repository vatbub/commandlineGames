package com.github.vatbub.commandlineGames.games;

/*-
 * #%L
 * commandlineGames
 * %%
 * Copyright (C) 2016 - 2017 Frederik Kammel
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import com.github.vatbub.commandlineGames.Main;
import logging.FOKLogger;

import java.util.Scanner;

public class QuickMath extends Game {
    @SuppressWarnings("FieldCanBeLocal")
    private final int increaseMaxValueEveryXCorrectAnswers = 5;
    @SuppressWarnings("FieldCanBeLocal")
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
            int random1;
            int random2;
            int operation = (int) (Math.random() * 4);
            do {
                random1 = (int) (Math.random() * (maxNumber - minNumber) + minNumber);
                random2 = (int) (Math.random() * (maxNumber - minNumber) + minNumber);
            } while (operation == 3 && (random2 == 0 || random1 / random2 != ((random1 * 1.0) / random2)));

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
