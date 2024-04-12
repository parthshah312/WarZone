package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controller.Game;
import controller.GameHelper;
import enumClass.GamePhase;


/**
 * Implementation of the game's initial phase.
 */
public class StartUpPhase extends PlayPhase implements Serializable {

    ShowMap s;
    String d_PlayerStrategy;
    public StartUpPhase(Game p_ge) {
        d_gameinstance = p_ge;
        d_PhaseName = "StartUpPhase";
        s=new ShowMap();
        d_PlayerStrategy = null;
    }
    private static final int MAX_PLAYERS = 5;

    /**
     * Player addition in the game:
     * There is a limit of 5 players that can be allocated.
     *
     * @param playersList List of players
     * @param playerName  Name of the player
     * @return true if the player is added successfully, else false
     */
    public boolean addPlayer(List<Player> playersList, String playerName) {

        // Check if the player already exists
        for (Player player : playersList) {
            if (player.getPlayerName().equals(playerName)) {
                System.out.println("------- Player '" + playerName + "' already exists!!! --------");
                return false; // Player already exists, return false
            }
        }

        if (playersList.size() >= MAX_PLAYERS) {
            System.out.println("-------- Cannot add more than 5  players. Limit reached----------");
            return false;
        }
        Player player = new Player(playerName);
        playersList.add(player);
        return true;
    }




    /**
     * Removing players
     *
     * @param playersList List of players
     * @param playerName  Name of the player
     * @return true if the player is removed successfully, else false
     */
    public boolean removePlayer(List<Player> playersList, String playerName) {
        Iterator<Player> iterator = playersList.iterator();
        while (iterator.hasNext()) {
            Player player = iterator.next();
            if (player.getPlayerName().equals(playerName)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
    /**
     * In charge of allocating players' regions.
     *
     * @param gameMap Game map
     * @param players List of players
     * @return true if successful, else false
     */

    public boolean assignTerritories(GameMap gameMap, List<Player> players) {
        int totalTerritories = gameMap.getTerritories().size();
        int playerIndex = 0;
        for (TerritoryDetails territory : gameMap.getTerritories().values()) {
            Player player = players.get(playerIndex);
            player.getOwnedTerritories().put(territory.getTerritoryID().toLowerCase(), territory);
            playerIndex = (playerIndex + 1) % players.size(); // Rotate players
        }
        return true;
    }

        public boolean assignCountries(GameMap p_map, ArrayList<Player> p_players) {
            d_gameinstance.d_LogEntry.setMessage("---- It is assigning territories to players ----");
            int l_numberOfPlayers = p_players.size();
            if (p_players.size() < 2) {
                System.out.println("---- It should be required minimum two players to play the game ----");
                return false;
            }
            int l_counter = 0;
            for (TerritoryDetails l_c : p_map.getTerritories().values()) {
                Player l_p = p_players.get(l_counter);
                l_p.getOwnedCountries().put(l_c.getTerritoryID().toLowerCase(), l_c);
                l_c.setOwnerPlayer(l_p);
                d_gameinstance.d_LogEntry.setMessage("---- Territory is : " + l_c.getTerritoryID().toLowerCase() + " assigned to player: " + l_p.getPlayerName()+" ----");
                if (l_counter >= l_numberOfPlayers - 1)
                    l_counter = 0;
                else
                    l_counter++;
            }
            return true;
        }
    @Override
	public void reinforce(){
		printInvalidCommandMessage();
	}

    @Override
    public void gamePlayer(String[] p_data,ArrayList<Player> p_players, String p_playerName) {
        try {
            for (int i = 1; i < p_data.length; i++) {
                if (p_data[i].equals("-add")) {
                    if (GameHelper.isValidPlayer(p_data[i + 1])) {
                        p_playerName = p_data[i + 1];
                        d_PlayerStrategy = p_data[i + 2];
                        boolean l_check = addPlayer(p_players, p_playerName);
                        if (l_check && GameHelper.isAlphabetic(p_data[i + 2])) {
                            for (Player l_p : d_gameinstance.d_Players) {
                                if (l_p.getPlayerName().equals(p_playerName)) {
                                    switch (d_PlayerStrategy) {
                                        case "human":
                                            l_p.setD_isHuman(true);
                                            break;
                                        case "aggressive":
                                            l_p.setStrategy(new AggresivePlayer(l_p, d_gameinstance.d_Map));
                                            l_p.setD_isHuman(false);
                                            break;
                                        case "benevolent":
                                            l_p.setStrategy(new BenevolentPlayer(l_p, d_gameinstance.d_Map));
                                            l_p.setD_isHuman(false);
                                            break;
                                        case "random":
                                            l_p.setStrategy(new RandomPlayer(l_p, d_gameinstance.d_Map));
                                            l_p.setD_isHuman(false);
                                            break;
                                        case "cheater":
                                            l_p.setStrategy(new CheaterPlayer(l_p, d_gameinstance.d_Map));
                                            l_p.setD_isHuman(false);
                                            break;
                                        default:
                                            l_p.setD_isHuman(true);
                                            break;
                                    }
                                }
                            }
                            if (l_check) {
                                System.out.println("-----Player added !!!! ------");
                                d_gameinstance.d_LogEntry.setMessage("----- Player added !!!!! " + p_playerName + " -----------");
                            } else {
                                System.out.println("-------- Can not add any more player. Max pool of 5 Satisfied -----");
                                d_gameinstance.d_LogEntry.setMessage("------ Can not add any more player. Max pool of 5 Satisfied --------");
                            }
                            d_gameinstance.d_GameGamePhase = GamePhase.STARTUP;
                        } else{
                            System.out.println("----- Invalid Player Name !!!! --------");
                            d_gameinstance.d_LogEntry.setMessage("------ Invalid Player Name !!!!! --------");
                        }
                    }else if (p_data[i].equals("-remove")) {
                        if (GameHelper.isValidPlayer(p_data[i + 1])) {
                            p_playerName = p_data[i + 1];
                            boolean l_check = removePlayer(p_players, p_playerName);
                            if (l_check) {
                                System.out.println("------- Player removed !!! ----------");
                                d_gameinstance.d_LogEntry.setMessage("---------- Player removed !!!!! " + p_playerName + " ---------");
                            } else {
                                System.out.println("-------- Player doesn't exist !!!!! --------- ");
                                d_gameinstance.d_LogEntry.setMessage("-------- Player doesn't exist !!!!! --------- ");
                            }
                            d_gameinstance.d_GameGamePhase = GamePhase.STARTUP;
                        } else
                            System.out.println("---------- Invalid Player Name -----------");
                        d_gameinstance.d_LogEntry.setMessage("--------- Invalid Player Name -------- ");
                    }
                }
            }
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("------ Invalid command format Should be: gameplayer -add playername -remove playername ------");
            d_gameinstance.d_LogEntry.setMessage("------ Invalid command format Should be: gameplayer -add playername -remove playername ------");
        }
        catch(Exception e) {
            System.out.println("------ Invalid command format Should be: gameplayer -add playername -remove playername ------");
            d_gameinstance.d_LogEntry.setMessage("------ Invalid command format Should be: gameplayer -add playername -remove playername ------");
        }

    }

    public void showMap(ArrayList<Player> p_players, GameMap p_map) {
        s.showMap(p_players, p_map);
    }


}
