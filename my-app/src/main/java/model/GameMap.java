package model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * GameMap hold the details of map in the game.
 * Consist of HashMaps to access territories and continents of the map with their names.
 * 
 *
 */
public class GameMap implements Serializable {
	public String d_MapName;
	public boolean d_Valid;
	public HashMap<String,Continent> d_Continents;
	public HashMap<String,TerritoryDetails> d_Territory;
	
	/**
	 * Create GameMap object without naming the map.
	 */
	public GameMap() {
		this.d_MapName= "";
		this.d_Continents= new HashMap<>();
		this.d_Territory= new HashMap<>();
		this.d_Valid= false;
	}
	
	/**
	 * Create GameMap object with naming the map.
	 * Initialize HashMaps for maintaining continents and territories.
	 * @param p_mapName Name of the map
	 */
	public GameMap(String p_mapName){
		this.d_MapName= p_mapName;
		this.d_Continents= new HashMap<>();
		this.d_Territory= new HashMap<>();
		this.d_Valid= false;
	}

	/**
	 * Setter method to set the d_Continents HashMap to the given HashMap parameter.
	 * @param p_continents HashMap for d_Continents
	 */
	public void setContinents(HashMap<String, Continent> p_continents) {
		this.d_Continents = p_continents;
	}

	public boolean getValid() {
		return this.d_Valid;
	}

	/**
	 * Getter method to return HashMap maintaining the list of territories in the map.
	 * @return return HashMap maintaining the list of territories in the map
	 */
	public HashMap<String, TerritoryDetails> getTerritories() {
		return this.d_Territory;
	}

	/**
	 * Setter method to set status for validity of the map.
	 * @param p_valid Indicate whether the map is valid for playing game or not
	 */
	public void setValid(boolean p_valid) {
		this.d_Valid = p_valid;
	}
	/**
	 * Setter method to set name of the map to the given name.
	 * @param p_mapName Name of the map
	 */
	public void setMapName(String p_mapName) {
		this.d_MapName = p_mapName;
	}
	/**
	 * 
	 * Getter method for map name
	 * 
	 * @return name of the map
	 */
	public String getMapName(){
		return this.d_MapName;
	}

/**
 * 
 * Getter method for continents
 * 
 * @return list of continents
 */
	public HashMap<String, Continent> getContinents(){
		return this.d_Continents;
	}



}
