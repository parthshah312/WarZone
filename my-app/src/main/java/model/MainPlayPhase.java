package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import controller.Game;
import view.App;


/**
 * Main play phase class
 */
public class MainPlayPhase extends PlayPhase  implements Serializable {

    App a;
    ShowMap s;

    /**
     * Main play phase constructor
     * @param p_g Game
     */
    public MainPlayPhase(Game p_g) {
        d_gameinstance = p_g;
        d_PhaseName = "MainPlay";
        s=new ShowMap();
    }

    @Override
    public void showMap(ArrayList<Player> players, GameMap map) {
        ShowMap.showMap(players, map);
    }

    @Override
    public void reinforce() {
        Iterator<Player> itr = d_gameinstance.d_Players.listIterator();
        while(itr.hasNext()) {
            Player p = itr.next();
            AllotArmies.allotArmies(p);
            d_gameinstance.d_LogEntry.setMessage("------- Assign reinforcement armies to player "+p.getPlayerName()+" -----------");
        }
    }

    @Override
    public void gamePlayer(String[] data, ArrayList<Player> players, String playerName) {
        printInvalidCommandMessage();
        d_gameinstance.d_LogEntry.setMessage("------ Invalid command in phase "+d_gameinstance.d_GamePhases.getD_PhaseName()+" -------");

    }

    @Override
    public boolean assignCountries(GameMap map, ArrayList<Player> players) {
        printInvalidCommandMessage();
        d_gameinstance.d_LogEntry.setMessage("------ Invalid command in phase "+d_gameinstance.d_GamePhases.getD_PhaseName()+" -------");
        return false;
    }
}
