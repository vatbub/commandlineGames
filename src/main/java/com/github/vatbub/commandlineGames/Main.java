package com.github.vatbub.commandlineGames;

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


import com.github.vatbub.commandlineGames.games.*;
import common.Common;
import logging.FOKLogger;

import java.util.Scanner;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        Common.setAppName("vatbubcommandlinegames");
        FOKLogger.enableLoggingOfUncaughtExceptions();
        FOKLogger.info(Main.class.getName(), "vatbub command line games version " + Common.getAppVersion());
        registerGames();
        Scanner scanner = new Scanner(System.in);

        Runtime.getRuntime().addShutdownHook(new Thread(scanner::close));

        if (args.length > 0) {
            if (args.length > 1) {
                FOKLogger.info(Main.class.getName(), "More than one command line argument will be ignored!");
            }

            // try to find a game that matches the name given as the first command line arg
            Game gameToLaunch = Game.getRegisteredGameByName(args[0]);
            if (gameToLaunch == null) {
                FOKLogger.severe(Main.class.getName(), "No such game: " + args[0]);
            } else {
                // game exists
                gameToLaunch.launch(scanner);
            }
        }

        while (true) {
            try {
                printGameNames();
                FOKLogger.info(Main.class.getName(), "Enter the index of the game to launch or type exit to exit:");
                String nextCommand = "";
                while (nextCommand.equals("")) {
                    nextCommand = scanner.nextLine();
                }
                try {
                    Game.getRegisteredGames().get(Integer.parseInt(nextCommand)).launch(scanner);
                } catch (NumberFormatException e) {
                    if (nextCommand.equalsIgnoreCase("exit")) {
                        System.exit(0);
                    } else {
                        FOKLogger.severe(Main.class.getName(), "Unknown command: " + nextCommand);
                    }
                }
            } catch (Exception e) {
                // IndexOutOfBoundException sometimes occurs in scanner.nextLine()
                FOKLogger.log(Main.class.getName(), Level.SEVERE, "An error occurred.", e);
                scanner = new Scanner(System.in);
            }
        }
    }

    private static void registerGames() {
        Game.registerGame(new GuessMyNumber());
        Game.registerGame(new QuickMath());
        Game.registerGame(new IsPrime());
        Game.registerGame(new ButtonPusher());
    }

    private static void printGameNames() {
        FOKLogger.info(Main.class.getName(), "Available games:");

        for (int i = 0; i < Game.getRegisteredGames().size(); i++) {
            FOKLogger.info(Main.class.getName(), "[" + i + "] " + Game.getRegisteredGames().get(i).getName());
        }
    }
}
