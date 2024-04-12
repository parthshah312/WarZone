package model;


import java.util.Random;

public class AggresivePlayer extends PlayerStrategy{
    private int d_OrderValue,d_MaximumArmies;
    TerritoryDetails d_StrongestTerritory,d_DefendingTerritory,d_MoveFromTerritory,d_InitialTerritory;
    boolean d_IsTest;
    public int d_TestReinforceArmies;

    /**
     * constructor
     */
    public AggresivePlayer(Player p_player, GameMap p_map) {
        super(p_player, p_map);
        d_StrongestTerritory = null;
        d_DefendingTerritory = null;
        d_InitialTerritory = null;
        d_MaximumArmies = 0;
        d_IsTest = false;
    }

    /**
     * method to find details of strongest territory
     */
    private void findStrongestTerritoryDetails()
    {
        int l_maxArmies = 0, l_numArmies;
        for(TerritoryDetails l_territoryDetails : this.d_Player.getOwnedTerritories().values()) {
            l_numArmies = l_territoryDetails.getNumberOfArmies();
            if( l_numArmies > l_maxArmies) {
                l_maxArmies = l_numArmies;
                d_StrongestTerritory = l_territoryDetails;
            }
        }

        if(l_maxArmies == 0)
            d_StrongestTerritory = findInitialTerritory();
    }
    protected TerritoryDetails toAttack()
    {
        if(d_StrongestTerritory!=null) {
            for (TerritoryDetails l_neighborTerritory : d_StrongestTerritory.getNeighbours().values()) {
                if (!this.d_Player.getOwnedTerritories().containsKey(l_neighborTerritory)) {
                    d_DefendingTerritory = l_neighborTerritory;
                    return d_DefendingTerritory;
                }
            }
        }
        return null;
    }

    protected TerritoryDetails toAttackFrom()
    {
        findStrongestTerritoryDetails();
        return d_StrongestTerritory;
    }

    protected TerritoryDetails toMoveFrom()
    {
        int l_maxArmies =0;
        findStrongestTerritoryDetails();

        if(d_StrongestTerritory!=null) {
            for (TerritoryDetails l_neighborTerritory : d_StrongestTerritory.getNeighbours().values()) {
                if (this.d_Player.getOwnedTerritories().containsKey(l_neighborTerritory)) {
                    int l_currentTerritoryArmies = l_neighborTerritory.getNumberOfArmies();
                    if (l_currentTerritoryArmies >= l_maxArmies) {
                        l_maxArmies = l_currentTerritoryArmies;
                        d_MoveFromTerritory = l_neighborTerritory;
                        d_MaximumArmies = l_maxArmies;
                    }
                }
            }
        }
        if(l_maxArmies == 0)
            return null;
        else
            return d_MoveFromTerritory;
    }

    /**
     * setter method for random order value
     */
    public void setRandomOrderValue(){
        Random l_random =new Random();
        d_OrderValue = l_random.nextInt(3);
    }

    /**
     * setter method for test order value
     * @param p_random random value
     */
    public void setTestOrderValue(int p_random){
        d_OrderValue = p_random;
    }

    /**
     * method to set test
     * @param p_isTest test
     */
    public void isTest(boolean p_isTest){
        d_IsTest = p_isTest;
    }

    /**
     * private method to find initial territory
     * @return details of intial territory
     */
    private TerritoryDetails findInitialTerritory() {
        for(TerritoryDetails l_territory : this.d_Player.getOwnedTerritories().values())
        {
            for (TerritoryDetails l_neighborTerritory : l_territory.getNeighbours().values()) {
                if(!this.d_Player.getOwnedTerritories().containsKey(l_neighborTerritory)){
                    d_InitialTerritory = l_territory;
                    return d_InitialTerritory;
                }
            }

        }
        return d_InitialTerritory;
    }

    
    @Override
    public order1 createOrder() {

        TerritoryDetails l_attackingTerritory,l_defendingTerritory,l_moveFromTerritory;
        l_attackingTerritory = toAttackFrom();
        l_defendingTerritory = toAttack();
        l_moveFromTerritory  = toMoveFrom();

        Random l_random = new Random();

        if(!d_IsTest)
            setRandomOrderValue();

        switch(d_OrderValue) {
            case 0:
                if(d_Player.getOwnedArmies() == 0)
                {
                    System.out.println("---- There is a reinforcement armies that are all used by the player ----");
                    break;
                }
                int l_reinforceArmies = l_random.nextInt(d_Player.getOwnedArmies());
                d_TestReinforceArmies = l_reinforceArmies;
                if(d_Player.getOwnedArmies() == 1)
                    l_reinforceArmies = 1;
                if (l_reinforceArmies != 0) {
                    if(d_StrongestTerritory!=null) {
                        System.out.println("---- There are armies deployed on territory :" + d_StrongestTerritory.getTerritoryID() + " " + l_reinforceArmies+" ----");
                        d_Player.setOwnedArmies(d_Player.getOwnedArmies() - l_reinforceArmies);
                        return new Order(d_Player, d_StrongestTerritory.getTerritoryID(), l_reinforceArmies);
                    }else{
                        findInitialTerritory();
                        if(d_InitialTerritory!=null) {
                            d_StrongestTerritory = d_InitialTerritory;
                            System.out.println("---- There are armies deployed on territory :" + d_InitialTerritory.getTerritoryID() + " " + l_reinforceArmies+" ----");
                            d_Player.setOwnedArmies(d_Player.getOwnedArmies() - l_reinforceArmies);
                            return new Order(d_Player, d_InitialTerritory.getTerritoryID(), l_reinforceArmies);
                        }
                    }
                }
                else
                    System.out.println("---- There is a invalid value for deploying reinforcement armies : "+l_reinforceArmies+" ----");
                return null;

            case 1:
                if(l_attackingTerritory.getNumberOfArmies() == 0)
                {
                    System.out.println("---- The number of armies in strongest territory is 0 so use the deploy command before advance ----");
                    return null;
                }
                if(l_defendingTerritory!=null) {
                    if(d_Player.doesCardExists("Bomb")) {
                        Random l_randomCard = new Random();
                        int l_value = l_randomCard.nextInt(2);
                        if (l_value == 0) {
                            d_Player.removeCard("Bomb");
                            return new Bomb(d_Player, l_defendingTerritory.getOwnerPlayer(), l_defendingTerritory.getTerritoryID());
                        }
                        else
                            break;
                    }
                    int l_randomVal;
                    if(l_attackingTerritory.getNumberOfArmies() > 0)
                        l_randomVal = l_random.nextInt(l_attackingTerritory.getNumberOfArmies());
                    else
                        return null;
                    if(l_randomVal!=0)
                        return new Advance(d_Player, l_attackingTerritory.getTerritoryID(), l_defendingTerritory.getTerritoryID(),l_randomVal , d_DefendingTerritory.getOwnerPlayer());
                    else
                        return null;
                }
                else
                    System.out.println("---- In this, neighbor does not exist for this territory :"+ l_attackingTerritory.getTerritoryID()+" ----");
                return null;


            case 2:
                if(l_moveFromTerritory!=null) {
                    if(d_Player.doesCardExists("Airlift")) {
                        Random l_randomCard = new Random();
                        int l_value = l_randomCard.nextInt(2);
                        if (l_value == 0) {
                            d_Player.removeCard("Airlift");
                            return new Airlift(d_Player, l_moveFromTerritory.getTerritoryID(), l_attackingTerritory.getTerritoryID(), d_MaximumArmies);
                        } else
                            break;
                    }
                    return new Advance(d_Player, l_moveFromTerritory.getTerritoryID(), l_attackingTerritory.getTerritoryID(), d_MaximumArmies, l_attackingTerritory.getOwnerPlayer());
                } else
                    return null;
        }
        return null;
    }

}
