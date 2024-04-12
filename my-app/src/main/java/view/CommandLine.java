package view;

import java.util.Iterator;
import java.util.Scanner;

import controller.Game;
import controller.TournamentEngine;
import enumClass.GamePhase;
import model.Player;

/**
 *
 * Here the commands are parsed to their respective functionallity.
 *
 */

public class CommandLine {

    /**
     *
     * Commands are read and passed to their function
     */
    static public void manageCommandLine(){
        App l_game = new App();
        Scanner sc = new Scanner(System.in);
        String l_cmd;
        String message;
        int traversalCounter = 0;
        GamePhase l_gamePhase = GamePhase.NULL;
        Game cmd = new Game();
        App game = new App();
        TournamentEngine tEngine;
        boolean valid = false;
        boolean loadGame = false;

        while (!valid) {
            System.out.println("---- Enter 1 to play single-game mode or 2 to play tournament mode ----");
            l_cmd = sc.nextLine();

            if (l_cmd.equals("1")) {
                while (!valid) {
                    System.out.println("---- Enter 1 to load a saved game or 2 to edit/load map for new game ----");
                    l_cmd = sc.nextLine();

                    if (l_cmd.equals("1")) {

                        valid = true;
                        loadGame = true;
                        System.out.println("---- To continue, select a game to load from the existing save games ----");
                        game.printSavedGames();
                        do {
                            l_cmd = sc.nextLine();
                            l_gamePhase = cmd.parseCommand(null, l_cmd);
                        } while (cmd.parseCommand(null, l_cmd).equals("Loaded successfully"));

                        traversalCounter = -1;
                        System.out.println(cmd.getGame().getPlayers());
                        for (Player player1 : cmd.getGame().getPlayers()) {

                            traversalCounter++;
                            if (player1 == cmd.getGame().getActivePlayer()) {
                                break;
                            }
                        }

                        cmd = new Game(cmd.getGame());
                    } else if (l_cmd.equals("2")) {
                        System.out.println("---- try to selecting a map from the below mentioned sample maps or create a new one: ----");
                        game.sampleMaps();

                        while (l_gamePhase != GamePhase.ISSUE_ORDERS) {
                            l_cmd = sc.nextLine();
                            l_gamePhase = cmd.parseCommand(null, l_cmd);
                        }

                        game.eachPlayerReinforcements(cmd);

                        int l_numberOfPlayers = cmd.d_Players.size();
                        int l_traversalCounter = 0;
                        while(true) {
                            while (l_traversalCounter < l_numberOfPlayers) {
                                Player l_p = cmd.d_Players.get(l_traversalCounter);
                                System.out.println("---- It is a " + l_p.getPlayerName() + " turn ----");
                                cmd.setD_ActivePlayer(l_p);
                                System.out.println("---- Player " + l_p.getPlayerName() + " has " + l_p.getOwnedArmies() + " Army units currently!!! ----");
                                l_gamePhase = GamePhase.ISSUE_ORDERS;
                                cmd.setGamePhase(l_gamePhase);
                                while (l_gamePhase != GamePhase.TURN) {
                                    if (l_p.getD_isHuman()) {
                                        l_cmd = sc.nextLine();
                                        l_gamePhase = cmd.parseCommand(l_p, l_cmd);
                                    } else {
                                        l_gamePhase = cmd.parseCommand(l_p, "");

                                    }
                                }
                                l_traversalCounter++;
                            }
                            l_gamePhase = GamePhase.ISSUE_ORDERS;
                            cmd.setGamePhase(l_gamePhase);
                            l_traversalCounter = 0;
                        }
                    }else {
                        System.out.println("---- it is not valid command so enter 1 to load game and enter 2 to load maps ----");
                        continue;
                    }
//
                    int l_numberOfPlayers = cmd.getGame().getPlayers().size();

                    while(true) {
                        while (traversalCounter < l_numberOfPlayers) {
                            Player l_p = cmd.d_Players.get(traversalCounter);
                            System.out.println("---- It is a " + l_p.getPlayerName() + " turn ----");
                            System.out.println("---- Player " + l_p.getPlayerName() + " has " + l_p.getOwnedArmies() + " Army units currently!!! ----");
                            l_gamePhase = GamePhase.ISSUE_ORDERS;
                            cmd.setGamePhase(l_gamePhase);
                            while (l_gamePhase != GamePhase.TURN) {
                                if (l_p.getD_isHuman()) {
                                    l_cmd = sc.nextLine();
                                    l_gamePhase = cmd.parseCommand(l_p, l_cmd);
                                } else {
                                    l_gamePhase = cmd.parseCommand(l_p, "");
                                }
                            }
                            traversalCounter++;
                        }
                        l_gamePhase = GamePhase.ISSUE_ORDERS;
                        cmd.setGamePhase(l_gamePhase);
                        traversalCounter = 0;

                    }
                }
            } else if (l_cmd.equals("2")) {
                tEngine = new TournamentEngine(cmd);
                System.out.println("---- Command"+cmd+" ----");
                do {
                    System.out.println("Command is need to be in the form of 'tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns");
                    l_cmd = sc.nextLine();

                    message = tEngine.parse(null, l_cmd);
                } while (!message.equals("success"));
                valid = true;
            } else {
                System.out.println("---- It is not valid Command so enter 1 to play single-game mode or 2 to play tournament mode ----");
            }
        }
    }
}
