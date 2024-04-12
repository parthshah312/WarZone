package model;

/**
 * 
 * Airlift class
 */
public class Airlift implements order1 {

    /**
     * Number of armies
     */
    private int d_NumArmies;

    /**
     * Source territory id
     */
    private String d_SourceTerritoryId;

    /**
     * Target territory id
     */
    private String d_TargetTerritoryId;

    /**
     * Player detail
     */
    private Player d_Player;
    /**
     * Airlift constructor
     * @param p_player Player details
     * @param p_sourceTerritoryId source territoryId
     * @param p_targetTerritoryId target territoryId
     * @param p_numArmies Number of armies
     */
    public Airlift(Player p_player,String p_sourceTerritoryId, String p_targetTerritoryId, int p_numArmies) {
        d_Player = p_player;
        d_SourceTerritoryId = p_sourceTerritoryId;
        d_TargetTerritoryId = p_targetTerritoryId;
        d_NumArmies = p_numArmies;
    }
    @Override
    public boolean execute() {

        TerritoryDetails l_cSource= d_Player.getOwnedTerritories().get(d_SourceTerritoryId.toLowerCase());
        int l_existingSourceArmies = l_cSource.getNumberOfArmies();
        l_existingSourceArmies -= d_NumArmies;
        l_cSource.setNumberOfArmies(l_existingSourceArmies);

        TerritoryDetails l_cTarget= d_Player.getOwnedTerritories().get(d_TargetTerritoryId.toLowerCase());
        if(l_cTarget != null){
        int l_existingTargetArmies = l_cTarget.getNumberOfArmies();
        l_existingTargetArmies += d_NumArmies;
        l_cTarget.setNumberOfArmies(l_existingTargetArmies);

        return true;
    }return false;}

}
