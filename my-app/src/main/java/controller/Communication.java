package controller;

import enumClass.GamePhase;
import model.GameMap;

/**
 * 
 * Class for communication
 * 
 */

public class Communication {
    public GamePhase d_gamePhase;
    public GameMap d_gameMap;

    /**
     * 
     * Constructor of communication
     * 
     * @param gameMap Object of map
     * @param gamePhase Object of phase of the game
     */

    Communication(GameMap gameMap, GamePhase gamePhase){
        this.d_gameMap  =gameMap;
        this.d_gamePhase = gamePhase;
    }

}
