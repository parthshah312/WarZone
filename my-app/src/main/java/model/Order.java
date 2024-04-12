package model;

import java.io.Serializable;

/**
 * class handles the functionality of executing orders.
 */
public class Order implements order1, Serializable {
	
	/**
	 * @param d_TerritoryId is for territory name
	 * @param d_Player player issuing deploy order
	 */
    private int d_NumArmies;
    private String d_TerritoryId;
    private Player d_Player;

  
    /**
     * The order object will be initialized with deploy details by this constructor.
     * @param p_player current player issuing deploy order
     * @param p_territoryId territory where armies will be deployed
     * @param p_numArmies total armies which will be deployed
     */
    public Order(Player p_player,String p_territoryId,int p_numArmies) {
        d_Player = p_player;
        d_TerritoryId = p_territoryId;
        d_NumArmies = p_numArmies;
    }

    /**
     * technique that implements the order
     * @return true if successful, else false
     */
    public boolean execute(){
        TerritoryDetails l_c= d_Player.getOwnedTerritories().get(d_TerritoryId.toLowerCase());

        if(l_c != null){

        int l_existingArmies = l_c.d_NumberOfArmies;
        l_existingArmies += d_NumArmies;
        l_c.d_NumberOfArmies = l_existingArmies;
        return true;
    }return false;}

    /**
     * Getter for current player
     * @return d_player
     */
    public Player getD_player() {
        return d_Player;
    }
}