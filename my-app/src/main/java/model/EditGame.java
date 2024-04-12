package model;
import java.util.ArrayList;


/**
 * Abstract class for edit game
 */

public abstract class EditGame extends GamePhases
{
    /**
     * Reinforce method
     */
    public void reinforce()
    {
        printInvalidCommandMessage();
        d_gameinstance.d_LogEntry.setMessage("Invalid command in phase " + d_gameinstance.d_GamePhases.getD_PhaseName());
    }

    /**
     * Game Player method
     */
    public void gamePlayer(String[] p_data, ArrayList<Player> p_players, String p_playerName)
    {
        printInvalidCommandMessage();
        d_gameinstance.d_LogEntry.setMessage("Invalid command in phase " + d_gameinstance.d_GamePhases.getD_PhaseName());
    }

    /**
     * Assign Territory method
     */
    public boolean assignCountries(GameMap p_map, ArrayList<Player> p_players) {
        printInvalidCommandMessage();
        d_gameinstance.d_LogEntry.setMessage("Invalid command in phase " + d_gameinstance.d_GamePhases.getD_PhaseName());
        return false;
    }

    /**
     * Show map method
     */
    public void showMap(ArrayList<Player> p_players, GameMap p_map) {
        printInvalidCommandMessage();
        d_gameinstance.d_LogEntry.setMessage("Invalid command in phase " + d_gameinstance.d_GamePhases.getD_PhaseName());
    }
}

    



	

