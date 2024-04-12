package model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * All the territory details pertaining to the chosen.map file are contained in the TerritoryDetails class.
 * It also keeps track of the nations that border it.
 * 
 *
 */
public class TerritoryDetails  implements Serializable {

	public int d_Index, d_NumberOfArmies;
	public String d_TerritoryId,d_InContinent;
	public HashMap<String, TerritoryDetails> d_Neighbours;
	private int d_XCoOrdinate;
	private int d_YCoOrdinate;
	 Player d_OwnerPlayer;
	/**
	 * Set TerritoryDetails object with default values.
	 */
	public TerritoryDetails(){}

	/**
	 * With the values from the argument parameters, create a TerritoryDetails object and set the rest to default.
	 * @param p_territoryId ID of the territory
	 * @param p_inContinent Name of the continent in which this territory belongs
	 */
	public TerritoryDetails(String p_territoryId, String p_inContinent){
		this.d_Neighbours = new HashMap<String, TerritoryDetails>();
		this.d_NumberOfArmies = 0;
		this.d_InContinent = p_inContinent;
		this.d_TerritoryId = p_territoryId;
	}
	
	/**
	 * * Creates the TerritoryDetails object based on the parameters sent in.
	 * This builder is employed for reading data from ".map" files.
	 * @param p_index index in the ".map" file as per Domination's conventions
	 * @param p_territoryId ID of the territory
	 * @param p_continentIndex index of the continent this territory belongs to
	 * @param p_xCoOrdinate x-coordinate on GUI map
	 * @param p_yCoOrdinate y-coordinate on GUI map
	 * @param p_map GameMap in which this territory resides
	 */
	public TerritoryDetails(String p_index, String p_territoryId, String p_continentIndex, String p_xCoOrdinate, String p_yCoOrdinate, GameMap p_map){
		this.d_Index = Integer.parseInt(p_index);
		this.d_TerritoryId = p_territoryId;
		for(Continent c : p_map.d_Continents.values()) {
			if(c.d_InMapIndex==Integer.parseInt(p_continentIndex)) {
				this.d_InContinent = c.d_ContinentId;
				//break;
			}	
		}	
		this.d_Neighbours = new HashMap<String, TerritoryDetails>();
		this.d_XCoOrdinate = Integer.parseInt(p_xCoOrdinate);
		this.d_YCoOrdinate = Integer.parseInt(p_yCoOrdinate);
		this.d_NumberOfArmies = 0;
	}
	public TerritoryDetails(String p_territoryId, String p_xCoOrdinate, String p_yCoOrdinate, String p_inContinent){
		this.d_Index = 0;
		this.d_TerritoryId = p_territoryId;
		this.d_InContinent = p_inContinent;
		this.d_Neighbours = new HashMap<String, TerritoryDetails>();
		this.d_XCoOrdinate = Integer.parseInt(p_xCoOrdinate);
		this.d_YCoOrdinate = Integer.parseInt(p_yCoOrdinate);
		this.d_NumberOfArmies = 0;
		this.d_OwnerPlayer = null;
	}
	/**
	 * Get number of armies in the territory
	 *
	 */
	public int getNumberOfArmies() {
		return this.d_NumberOfArmies;
	}
	public String getInContinent() {
		return this.d_InContinent;
	}
	/**
	 * Set number of armies in the territory
	 * @param p_numberOfArmies number of armies to be set in the territory
	 */
	public void setNumberOfArmies(int p_numberOfArmies) {
		this.d_NumberOfArmies = p_numberOfArmies;
	}
	public int getxCoOrdinate() {
		return this.d_XCoOrdinate;
	}

	/**
	 * Getter method to fetch y-coordinate value
	 * @return returns d_yCoOrdinate
	 */
	public int getyCoOrdinate() {
		return this.d_YCoOrdinate;
	}

	/**
	 * Returns the name of the territory
	 * @return returns d_territoryName
	 */
	public String getTerritoryID() {
		return d_TerritoryId;
	}

	/**
	 * returns a hash map containing the neighbors, with the TerritoryDetails object references for the neighbors' addresses and their lowercase names as keys.
	 * @return returns d_neighbours of this territory
	 */
	public HashMap<String, TerritoryDetails> getNeighbours(){
		return this.d_Neighbours;
	}

	/**
	 * Overrides the String representation of the object.
	 * @return A string representation of the TerritoryDetails object.
	 */
	@Override
	public String toString() {
		return "Territory [territoryName=" + d_TerritoryId + "]";
	}

	public Player getOwnerPlayer() {
		return d_OwnerPlayer;
	}
	/**
	 * Settter for the player who is owning this territory currently
	 * @param ownerPlayer Player who is owning this territory
	 */
	public void setOwnerPlayer(Player ownerPlayer) {
		this.d_OwnerPlayer = ownerPlayer;
	}
	
}
