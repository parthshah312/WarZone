package model;


/**
 * Bomb class
 */
public class Bomb implements order1{

    /**
     * Territory id
     */
    private String d_TerritoryId;

    /**
     * Player detail
     */
    private Player d_Player;

    /**
     * Current player
     */
    private Player d_CPlayer;
    
    /**
     * Bomb constructor
     * @param p_cplayer current player
     * @param p_player target player
     * @param p_territoryId territory id
     */
    public Bomb(Player p_cplayer, Player p_player,String p_territoryId) {
        d_Player = p_player;
        d_CPlayer = p_cplayer;
        d_TerritoryId = p_territoryId;
    }

    @Override
    public boolean execute() {
        if(d_CPlayer.d_NegotiateList.contains(d_Player)){
            System.out.println(d_CPlayer.getPlayerName()+" has negotiated "+d_Player.getPlayerName());
            return false;
        }
        TerritoryDetails l_c= d_Player.getOwnedTerritories().get(d_TerritoryId.toLowerCase());
        if(l_c != null){
        int l_existingArmies = l_c.getNumberOfArmies();
        double armies = Double.valueOf(l_existingArmies / 2);
        l_c.setNumberOfArmies((int)armies);
        return true;
    }
        return false;
    }
}