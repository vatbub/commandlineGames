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


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Template for a game.
 * Games must be built in a way that the {@link #launch(Scanner)}-method can be called multiple times without interference.
 * The game loader will take care of the task to keep only one instance of a class in memory so there is no need for static methods.
 */
public abstract class Game {
    private static List<Game> registeredGames = new ArrayList<>();

    /**
     * Registers a game
     *
     * @param game The game to register
     */
    public static void registerGame(Game game) {
        registeredGames.add(game);
    }

    /**
     * UNregisters the specified game
     *
     * @param game The game to unregister.
     * @return {@code true} if the game was registered.
     */
    public static boolean unregisterGame(Game game) {
        return registeredGames.remove(game);
    }

    /**
     * Returns the list of games that were registered using {@link #registerGame(Game)}
     *
     * @return the list of games that were registered using {@link #registerGame(Game)}
     */
    public static List<Game> getRegisteredGames() {
        return registeredGames;
    }

    /**
     * Looks for a registered game with the given name
     *
     * @param name The name of the game to look for
     * @return The game with the specified name or {@code null} if no such game was registered.
     */
    public static Game getRegisteredGameByName(String name) {
        for (Game game : getRegisteredGames()) {
            if (game.getName().equals(name)) {
                return game;
            }
        }
        return null;
    }

    /**
     * Must return a human- and machine-readable name of the game. Must not contain spaces.
     *
     * @return a human- and machine-readable name of the game.
     */
    public abstract String getName();

    /**
     * Launches the game once.
     *
     * @param in The scanner which reads from {@code System.in}. Do not close this scanner!
     */
    public abstract void launch(Scanner in);
}
