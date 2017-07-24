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


import java.util.Scanner;

public class GuessMyNumber extends Game {
    @SuppressWarnings("FieldCanBeLocal")
    private final int minNumber = 0;
    @SuppressWarnings("FieldCanBeLocal")
    private final int maxNumber = 100;

    @Override
    public String getName() {
        return "GuessMyNumber";
    }

    @Override
    public void launch(Scanner in) {
        int numberOfGuesses = 0;
        int number = (int) (Math.random() * (maxNumber - minNumber) + minNumber);
        int lastGuess = Integer.MIN_VALUE;

        while (lastGuess != number) {
            System.out.println("Guess my number between " + minNumber + " and " + maxNumber + ":");
            numberOfGuesses = numberOfGuesses + 1;
            lastGuess = in.nextInt();
            if (lastGuess > number) {
                System.out.println("Lower!");
            } else if (lastGuess < number) {
                System.out.println("Higher!");
            }
        }

        // User gave the correct answer
        System.out.println("Well done! The number was " + number + " and it took you " + numberOfGuesses + " guesses to get it right!");
    }
}
