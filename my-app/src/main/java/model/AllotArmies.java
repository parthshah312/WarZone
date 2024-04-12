package model;

/**
 * it basically assign the armies as per the rules
 */
public class AllotArmies {
    /**
     * @param p_player current player
     */
    public static void allotArmies(Player p_player){
        int l_totalControlValue = 0;
        int l_totalReinforcementArmies;
        if(p_player.getOwnedTerritories().size() >= 9){
            if(p_player.getOwnedContinents().size()> 0){
                for(Continent l_c:p_player.getOwnedContinents().values()){
                    l_totalControlValue += l_c.d_ControlValue;
                }
                l_totalReinforcementArmies = (int)(p_player.getOwnedTerritories().size()/3) + l_totalControlValue;
            }
            else{
                l_totalReinforcementArmies = (int)(p_player.getOwnedTerritories().size()/3);
            }
        }
        else{
            l_totalReinforcementArmies = 3;
        }
        p_player.setOwnedArmies(l_totalReinforcementArmies);
    }
}