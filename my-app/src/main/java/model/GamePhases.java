package model;

import java.util.ArrayList;

import controller.Game;

/**
 * It maintains the current phase of the game.
 */
public abstract class GamePhases {
	Game d_gameinstance;
	public String d_PhaseName;
	
	/**
	 * return game state
	 * @return d_Phase it is current state.
	 */
	public String getD_PhaseName() {
		return d_PhaseName;
	}
	
	/**
	 * Method to randomly assign countries to players
	 * @param p_gamemap Loaded map
	 * @param p_gameplayers list of the players in game
	 * @return returns boolean value
	 */
	abstract public boolean assignCountries(GameMap p_gamemap, ArrayList<Player> p_gameplayers);
	
	/**
	 * Method to Load the map.
	 * @param p_data it is array of strings passed as command  
	 * @param p_map name of the map
	 */
	abstract public void loadMap(String[] p_data, String p_map) ;
	
	/**
	 * Method to show the loaded map
	 * @param p_gamemap refrence of the map to be loaded.
	 */
	abstract public void showMap(GameMap p_gamemap) ;
	
	/**
	 * Method to edit the map
	 * @param p_data it is array of strings passed as command 
	 * @param p_gamemap name of the map
	 */
	abstract public void editMap(String[] p_data, String p_gamemap) ;
	
	/**
	 * Method to save the edited or created map.
	 * @param p_data it is array of strings passed as command 
	 * @param p_gamemap name of the map
	 */
	abstract public void savemap(String[] p_data, String p_gamemap) ;
	
	/**
	 * Method to edit neighbors 
	 * @param p_data it is array of strings passed as command 
	 * @param p_countryId Country id/name
	 * @param p_neighborCountryId neighbor country id/name.
	 */
	abstract public void editNeighbour(String[] p_data, String p_countryId, String p_neighborCountryId) ;
	
	/**
	 * Method to edit country.
	 * @param p_data it is array of strings passed as command  
	 * @param p_continentId Continent id/name
	 * @param p_countryId Country id/name
	 */
	abstract public void editCountry(String[] p_data, String p_continentId, String p_countryId);
	
	/**
	 * Method to edit continent 
	 * @param p_data it is array of strings passed as command 
	 * @param p_continentId Continent id/name
	 * @param p_controlValue controlvalue of continent
	 */
	abstract public void editContinent(String[] p_data, String p_continentId, int p_controlValue);
	
	/**
	 * Method to provide reinforcement.
	 */
	abstract public void reinforce() ;
	
	/**
	 * Method to call remove and add players functionality. 
	 * @param p_data it is array of strings passed as command 
	 * @param p_gameplayers  are the list of players present in the game
	 * @param p_playerName name of the player.
	 */
	abstract public void gamePlayer(String[] p_data, ArrayList<Player> p_gameplayers, String p_playerName) ;
	
	/**
	 * Method will show map with the player having countries.
	 * @param p_gameplayers are the list of players present in the game
	 * @param p_gamemap the map that is loaded.
	 */

	abstract public void showMap(ArrayList<Player> p_gameplayers, GameMap p_gamemap );
	
	/**
	 * Method for validation of map.
	 */
	abstract public void validatemap();
	
	/**
	 * method to show invalid command passed during particular state.
	 */
	public void printInvalidCommandMessage(){
		System.out.println("Invalid command in state "+ d_PhaseName);
	}
}

