package model;

import java.util.ArrayList;

/**
 * Log buffer class
 */

public class LogBuffer extends Observable {

    private GameMap d_Map;

    private GamePhases d_GamePhase;

    private ArrayList<Player> d_Players;

    private Player d_ActivePlayer;

    String d_Command;

    String d_PhaseValue;

    String d_Message;

    /**
     * Log buffer constructor
     */
    public LogBuffer(){
        d_Map = new GameMap();
        d_Players = new ArrayList<Player>();
        d_ActivePlayer = null;
        d_GamePhase = null;
        d_PhaseValue = "";
        d_Command = "";
        d_Message = "";
    }

    /**
     * getter function for map
     * @return map
     */
    public GameMap getMap() {
        return this.d_Map;
    }

    /**
     * setter function for map
     * @param p_map map
     */
    public void setMap(GameMap p_map) {
        this.d_Map = p_map;
    }


    /**
     * setter method for phase value
     * @param p_phaseValue phase value
     */
    public void setPhaseValue(String p_phaseValue)
    {
        d_PhaseValue = p_phaseValue;

    }

    /**
     * getter method for phase value
     * @return phase value
     */
    public String getPhaseValue()
    {
        return d_PhaseValue;
    }

    boolean d_IsGamePhaseSet = false;
    boolean d_IsCommandSet=false;
    boolean d_IsMessageSet=false;

    /**
     * setter method for game phase
     * @param p_gamePhase game phase
     */
    public void setGamePhase(GamePhases p_gamePhase) {

        if(d_GamePhase!=null && this.d_GamePhase.getD_PhaseName()==p_gamePhase.getD_PhaseName())
            return;
        else
            d_GamePhase = p_gamePhase;

        setPhaseValue("------ In "+d_GamePhase.getD_PhaseName()+" Phase -------");
        d_IsGamePhaseSet = true;
        notifyObservers(this);
    }

    /**
     * getter method for game phase
     * @return current phase of game
     */
    public boolean getGamePhaseSet(){
        return d_IsGamePhaseSet;
    }

    /**
     * setter method for game phase
     * @param p_isGamePhaseSet game phase
     */
    public void setGamePhaseSet(boolean p_isGamePhaseSet){
        d_IsGamePhaseSet=p_isGamePhaseSet;
    }

    /**
     * getter method for command set
     * @return command set boolean value
     */
    public boolean getCommandSet(){
        return d_IsCommandSet;
    }

    /**
     * setter method for command set
     * @param p_isCommandSet boolean value 
     */
    public void setCommandSet(boolean p_isCommandSet){
        d_IsCommandSet=p_isCommandSet;
    }

    /**
     * getter method for message set
     * @return true if is a message set
     */
    public boolean getMessageSet(){
        return d_IsMessageSet;
    }

    /**
     * setter method for message set
     * @param p_isMessageSet bollean value
     */
    public void setMessageSet(boolean p_isMessageSet){
        d_IsMessageSet=p_isMessageSet;
    }

    /**
     * setter method for command
     * @param p_command command name
     */
    public void setCommand(String p_command) {
        d_Command = p_command;
        d_IsCommandSet=true;
        notifyObservers(this );
    }

    /**
     * getter method for command
     * @return command
     */
    public String getCommand(){
        return d_Command;
    }

    /**
     * setter method for message
     * @param p_message message
     */
    public void setMessage(String p_message){
        d_Message = p_message;
        d_IsMessageSet=true;
        notifyObservers(this);
    }

    /**
     * getter method for message
     * @return message
     */
    public String getMessage(){
        return d_Message;
    }

    /**
     * getter method for player list
     * @return list of players
     */
    public ArrayList<Player> getPlayers() {
        return this.d_Players;
    }

    /**
     * setter method for player
     * @param p_players list of players
     */
    public void setPlayers(ArrayList<Player> p_players) {
        this.d_Players = p_players;
    }


}
