package model;

import java.util.ArrayList;

/**
 * Play phase abstract class
 */

public abstract class PlayPhase extends GamePhases{


	
	/** getter method for phase name
	 * @return phase of the game 
	 */
	public String getD_PhaseName() {
		return d_PhaseName;
	}

	
    
	/** load map method
	 * @param p_data list of data
	 * @param p_mapName map name
	 */
	public void loadMap(String[] p_data, String p_mapName) {

		printInvalidCommandMessage();
		d_gameinstance.d_LogEntry.setMessage("------- Invalid command in phase "+d_gameinstance.d_GamePhases.getD_PhaseName()+ " -----------");

	}
		
	
	/**
	 * show map method
	 * @param p_map map of game
	 */
	public void showMap(GameMap p_map) {
		printInvalidCommandMessage();
		d_gameinstance.d_LogEntry.setMessage("------- Invalid command in phase "+d_gameinstance.d_GamePhases.getD_PhaseName()+ " -----------");

	}

	
	
	/** 
	 * edit continent method
	 * @param p_data list of data
	 * @param p_continentId continent id
	 * @param p_controlValue continent's control value
	 */
	public void editContinent(String[] p_data, String p_continentId, int p_controlValue) {
		printInvalidCommandMessage();
		d_gameinstance.d_LogEntry.setMessage("------- Invalid command in phase "+d_gameinstance.d_GamePhases.getD_PhaseName()+ " -----------");

	}

	
	/** 
	 * edit country method
	 * @param p_data list of data
	 * @param p_continentId continent id
	 * @param p_countryId country id
	 */
	public void editCountry(String[] p_data, String p_continentId, String p_countryId) {
		printInvalidCommandMessage();
		d_gameinstance.d_LogEntry.setMessage("------- Invalid command in phase "+d_gameinstance.d_GamePhases.getD_PhaseName()+ " -----------");

	}

	/**
	 * edit neighbour method
	 * @param p_data list of data
	 * @param p_countryId country id
	 * @param p_neighborCountryId neighbouring country id
	 */
	public void editNeighbour(String[] p_data, String p_countryId, String p_neighborCountryId) {
		printInvalidCommandMessage();
		d_gameinstance.d_LogEntry.setMessage("------- Invalid command in phase "+d_gameinstance.d_GamePhases.getD_PhaseName()+ " -----------");

	}

	
	/** 
	 * save map method
	 * @param p_data list of data
	 * @param p_mapName map name
	 */
	public void savemap(String[] p_data, String p_mapName) {
		printInvalidCommandMessage();
		d_gameinstance.d_LogEntry.setMessage("------- Invalid command in phase "+d_gameinstance.d_GamePhases.getD_PhaseName()+ " -----------");
	}

	
	/** 
	 * edit map method
	 * @param p_data list of data
	 * @param p_mapName map name
	 */
	public void editMap(String[] p_data, String p_mapName) {
		printInvalidCommandMessage();
		d_gameinstance.d_LogEntry.setMessage("-------- Invalid command in phase "+d_gameinstance.d_GamePhases.getD_PhaseName()+" -----------");

	}

	/**
	 * validate map method
	 */
	public void validatemap(){
		printInvalidCommandMessage();
		d_gameinstance.d_LogEntry.setMessage("------- Invalid command in phase "+d_gameinstance.d_GamePhases.getD_PhaseName()+ " -----------");

	}


/**
 * game player method
 * @param p_data list of data
 * @param p_gameplayers list of players
 * @param p_playerName name of the player
 */
    public void gamePlayer(String[] p_data, ArrayList<Player> p_gameplayers, String p_playerName){
        printInvalidCommandMessage();
    }


}
