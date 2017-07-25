# How to contribute
First of all thank you for your contribution!

But before you commit your code and submit that pull request, please make sure you respect the following things:

# Spirit of the games
If you submit a new game, please keep in mind that the entire project is in the spirit of the command line. Do not launch any gui games, please, keep it on the command line only.

Also, games should be designed in a way that they can be launched and exited quickly. Always give the user the option to exit, e. g. by typing the word `exit` and avoid long loading times.

# Technical requirements for games
Games need to extend the class `Game` and need to be registered in order to be shown in the list of available games. The registration is done in the `registerGames()`-method in the `Main`-class.