package model;

import java.util.Collection;
import java.util.Random;

public class BenevolentPlayer extends PlayerStrategy{

    TerritoryDetails d_SourceTerritory,d_WeakTerritory;
    Random random = new Random();
    public BenevolentPlayer(Player p_player, GameMap p_map) {
        super(p_player, p_map);
        d_SourceTerritory = null;
        d_WeakTerritory = null;
    }

    /**
     * method to find details of weakest territory
     * @return details of weakest territory
     */
    private TerritoryDetails tofindWeakestTerritoryDetails() {
        Collection<TerritoryDetails> l_territories=d_Player.getOwnedTerritories().values();
        int l_minArmies = 100;
        for(TerritoryDetails l_territoryDetails : l_territories) {
            int l_numArmies = l_territoryDetails.getNumberOfArmies();
            if( l_numArmies < l_minArmies) {
                l_minArmies = l_numArmies;
                d_WeakTerritory = l_territoryDetails;
            }
        }

        if (d_WeakTerritory == null) {
            d_WeakTerritory = d_Player.getOwnedTerritories().get(0);
        }
        return d_WeakTerritory;
    }
    @Override
    protected TerritoryDetails toMoveFrom() {
        Object[] values = d_Player.getOwnedTerritories().values().toArray();
        int totalC = values.length - 1;
        if(d_Player.getOwnedTerritories().size() != 0){
            Object randomValue = values[random.nextInt(totalC + 1)];
            d_SourceTerritory = (TerritoryDetails) randomValue;
        }

        return d_SourceTerritory;
    }
    protected TerritoryDetails toAdvance()
    {
        if(d_SourceTerritory!=null) {
            tofindWeakestTerritoryDetails();
            for (TerritoryDetails l_neighborTerritory : d_SourceTerritory.getNeighbours().values()) {
                if (this.d_Player.getOwnedTerritories().containsKey(l_neighborTerritory.getTerritoryID()) && (l_neighborTerritory == d_WeakTerritory)) {
                    return d_WeakTerritory;
                }
            }
        } else{
            return null;
        }
        return null;
    }
    @Override
    public order1 createOrder() {

        TerritoryDetails l_sourceTerritory, l_advanceTerritory;
        l_sourceTerritory = toMoveFrom();
        l_advanceTerritory = toAdvance();

        int l_rndOrder = random.nextInt(2);
        int rnd_num_of_armies_pool = d_Player.getOwnedArmies();
        TerritoryDetails l_cD = tofindWeakestTerritoryDetails();

        switch(l_rndOrder) {
            case 0:
                if (l_cD!= null) {
                    d_Player.setOwnedArmies(0);
                    return new Order(d_Player, l_cD.getTerritoryID(),rnd_num_of_armies_pool);
                } else {
                    System.out.println("---- It can not be deployed on weak territory ----");
                }
                break;
            case 1:
                if(l_sourceTerritory.getNumberOfArmies() == 0)
                {
                    System.out.println("---- The number of armies in strongest territory is 0 so deploy the command before advance ----");
                    return null;
                }

                if(l_advanceTerritory!=null) {
                    int l_randomVal;
                    if (l_sourceTerritory.getNumberOfArmies() > 0)
                        l_randomVal = random.nextInt(l_sourceTerritory.getNumberOfArmies());
                    else
                        return null;

                    if (l_randomVal != 0)
                        return new Advance(d_Player, l_sourceTerritory.getTerritoryID(), l_advanceTerritory.getTerritoryID(), l_randomVal, l_advanceTerritory.getOwnerPlayer());
                    else
                        System.out.println("---- Neighbor does not exist for this territory"+ l_sourceTerritory.getTerritoryID()+" ----");
                    break;
                }
        }

        return null;
    }

    @Override
    protected TerritoryDetails toAttackFrom() {
        return null;
    }

    @Override
    protected TerritoryDetails toAttack() {
        return null;
    }

}
