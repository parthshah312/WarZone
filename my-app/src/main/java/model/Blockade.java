package model;

/**
 * Blockade class
 */

public class Blockade implements order1{

    /**
     * Territory id
     */
    private String d_TerritoryId;

    /**
     * Player detail
     */
    private Player d_Player;
    
    /**
     * Blockade constructor
     * @param p_player Player detail
     * @param p_territoryId territory id
     */
    public Blockade(Player p_player,String p_territoryId) {
        d_Player = p_player;
        d_TerritoryId = p_territoryId;
    }

    @Override
    public boolean execute() {

        TerritoryDetails l_c= d_Player.getOwnedTerritories().get(d_TerritoryId.toLowerCase());
        if(l_c != null){
        int l_existingArmies = l_c.getNumberOfArmies();
        l_existingArmies *= 3;
        l_c.setNumberOfArmies(l_existingArmies);
        System.out.println("----The armies of the Player is now 3 times of the existing armies"+l_existingArmies+"----");
        d_Player.getOwnedTerritories().remove(l_c.getTerritoryID().toLowerCase());
        return true;
    }return false;}

}
