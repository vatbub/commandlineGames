package com.github.vatbub.commandlineGames.games;

import logging.FOKLogger;

import java.util.Scanner;
import java.util.logging.Level;

public class ButtonPusher extends Game {
    /**
     * Must return a human- and machine-readable name of the game. Must not contain spaces.
     *
     * @return a human- and machine-readable name of the game.
     */
    @Override
    public String getName() {
        return "ButtonPusher";
    }

    /**
     * Launches the game once.
     *
     * @param in The scanner which reads from {@code System.in}. Do not close this scanner!
     */
    @Override
    public void launch(Scanner in) {
        final int[] counter = {0};
        final boolean[] end = {false};

        FOKLogger.info(ButtonPusher.class.getName(), "You have one minute to press the Enter key as often as you can!");
        FOKLogger.info(ButtonPusher.class.getName(), "The timer starts once you hit Enter for the first time.");
        FOKLogger.info(ButtonPusher.class.getName(), "Type 'exit' to exit the mini-game.");

        String res = in.nextLine();
        if (!res.equalsIgnoreCase("exit")) {

            Thread inputThread = new Thread(() ->{
                while (!end[0]) {
                    String res2 = in.nextLine();
                    if (res2.equalsIgnoreCase("exit")) {
                        // gracefully exit the future task
                        end[0] =true;
                    } else if (!end[0]) {
                        counter[0]++;
                    }
                }
            });
            inputThread.setName("inputThread");
            inputThread.start();

            try {
                Thread.sleep(10000);
                end[0] =true;
            } catch (InterruptedException e) {
                FOKLogger.log(ButtonPusher.class.getName(), Level.INFO, "An error occurred.", e);
            }
        }

        if (counter[0] == 1) {
            FOKLogger.info(ButtonPusher.class.getName(), "You have pressed the Enter key " + counter[0] + " time!");
        } else {
            FOKLogger.info(ButtonPusher.class.getName(), "You have pressed the Enter key " + counter[0] + " times!");
        }
    }
}
