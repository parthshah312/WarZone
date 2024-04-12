package model;

import java.util.Random;

public class RandomPlayer extends PlayerStrategy {
    Random rand = new Random();
    TerritoryDetails d_RandomTerritory, d_RandomNeighbour, d_RandomTerritoryWithArmies;
    TerritoryDetails l_attackingTerritory, l_defendingTerritory, l_advanceTerritory;
    public RandomPlayer(Player p_player, GameMap p_map) {
        super(p_player, p_map);
        d_RandomTerritory = null;
        d_RandomNeighbour = null;
        d_RandomTerritoryWithArmies = null;
    }

    /**
     * method to get neighbour of target territory
     * @param d_RandomTerritoryWithArmies random terriroty
     * @return neighbour
     */
    public TerritoryDetails targetTerritoryNeighbour(TerritoryDetails d_RandomTerritoryWithArmies) {
        TerritoryDetails temp = null;
        if(d_RandomTerritoryWithArmies == null){
            return null;
        }else {
            for (TerritoryDetails l_neighborTerritory : d_RandomTerritoryWithArmies.getNeighbours().values()) {
                if (!this.d_Player.getOwnedTerritories().containsKey(l_neighborTerritory.getTerritoryID())) {
                    temp = l_neighborTerritory;
                    return temp;
                }
            }
        }
        return null;
    }

    /**
     * method to find a random territory
     * @return details of random territory
     */
    public TerritoryDetails findRandomTerritory() {
        d_RandomTerritory = null;
        Object[] values = d_Player.getOwnedTerritories().values().toArray();
        System.out.println(d_Player.getOwnedTerritories());
        System.out.println(d_Player.getPlayerName());
        int totalC = values.length - 1;
        if(d_Player.getOwnedTerritories().size() != 0){
            Object randomValue = values[rand.nextInt(totalC + 1)];
            d_RandomTerritory = (TerritoryDetails) randomValue;
        }
        return d_RandomTerritory;
    }

    /**
     * method to find random territory
     * @return details of random territory
     */
    private TerritoryDetails findOtherRandomTerritory() {
        d_RandomTerritory = null;
        if(d_RandomTerritoryWithArmies == null){
            return d_RandomTerritory;
        }
        else {
            TerritoryDetails temp = null;
            boolean t = true;
            do {
                d_RandomTerritory = findRandomTerritory();
                if (d_RandomTerritory != d_RandomTerritoryWithArmies) {
                    temp = d_RandomTerritory;
                    t = false;
                }
            }while (t);
            return temp;
        }
    }

    /**
     * method to attack terrirtoy
     * @param d_RandomTerritoryWithArmies random territory
     * @return random neighbour
     */
    protected TerritoryDetails toAttack(TerritoryDetails d_RandomTerritoryWithArmies) {
        if (d_RandomTerritoryWithArmies == null) {
            return null;
        } else {
            d_RandomNeighbour = null;
            for (TerritoryDetails l_neighborTerritory : d_RandomTerritoryWithArmies.getNeighbours().values()) {
                if (!this.d_Player.getOwnedTerritories().containsKey(l_neighborTerritory.getTerritoryID())) {
                    d_RandomNeighbour = l_neighborTerritory;
                    return d_RandomNeighbour;
                }
            }
            return d_RandomNeighbour;
        }
    }

    /**
     * method to advance
     * @param d_RandomTerritoryWithArmies random territory
     * @return random neighbour
     */
    protected TerritoryDetails toAdvance(TerritoryDetails d_RandomTerritoryWithArmies) {
        if (d_RandomTerritoryWithArmies == null) {
            return null;
        } else {
            d_RandomNeighbour = null;
            for (TerritoryDetails l_neighborTerritory : d_RandomTerritoryWithArmies.getNeighbours().values()) {
                if (this.d_Player.getOwnedTerritories().containsKey(l_neighborTerritory.getTerritoryID())) {
                    d_RandomNeighbour = l_neighborTerritory;
                    break;
                }
            }
            return d_RandomNeighbour;
        }
    }

    @Override
    protected TerritoryDetails toMoveFrom() {
        return null;
    }
    protected TerritoryDetails toAttackFrom() {
        d_RandomTerritoryWithArmies = null;
        int l_maxArmies = 0, l_numArmies;
        for (TerritoryDetails l_TerritoryDetails : this.d_Player.getOwnedTerritories().values()) {
            l_numArmies = l_TerritoryDetails.getNumberOfArmies();
            if (l_numArmies > l_maxArmies) {
                d_RandomTerritoryWithArmies = l_TerritoryDetails;
                return d_RandomTerritoryWithArmies;
            }
        }
        return d_RandomTerritoryWithArmies;
    }

    @Override
    protected TerritoryDetails toAttack() {
        return null;
    }
    public Player getRandomPlayer() {
        Player l_diffPlayer = null;
        boolean t = true;
        while (t) {
            Object[] values = d_Map.getTerritories().values().toArray();
            Object randomValue = values[rand.nextInt(values.length)];
            d_RandomTerritory = (TerritoryDetails) randomValue;
            if (!d_Player.getOwnedTerritories().containsValue(d_RandomTerritory) && d_RandomTerritory.getOwnerPlayer() != null) {
                l_diffPlayer = d_RandomTerritory.getOwnerPlayer();
                t = false;
            }
        }
        return l_diffPlayer;
    }
    @Override
    public order1 createOrder() {
        l_attackingTerritory = toAttackFrom();
        l_defendingTerritory = toAttack(l_attackingTerritory);
        l_advanceTerritory = toAdvance(l_attackingTerritory);

        int rndOrder = rand.nextInt(7);
        int rnd_num_of_armies_pool = d_Player.getOwnedArmies();
        switch (rndOrder) {
            case (0):
                if(d_Player.getOwnedArmies() != 0) {
                    d_Player.setOwnedArmies(0);
                    TerritoryDetails ex = findRandomTerritory();
                    if(ex != null){
                        return new Order(d_Player, ex.getTerritoryID(), rnd_num_of_armies_pool);
                    }else
                        return null;
                }else{
                    return null;
                }


            case (1):
                if (l_defendingTerritory != null) {
                    System.out.println("---- Defending territory" + l_defendingTerritory.getOwnerPlayer()+" ----");
                    return new Advance(d_Player, l_attackingTerritory.getTerritoryID(), l_defendingTerritory.getTerritoryID(), rand.nextInt(l_attackingTerritory.getNumberOfArmies()), l_defendingTerritory.getOwnerPlayer());
                }
                else
                    System.out.println("---- The neighbor does not exist for this territory or Source territory does not have Armies ----");
                return null;


            case (2):
                if (l_advanceTerritory != null)
                    return new Advance(d_Player, l_attackingTerritory.getTerritoryID(), l_advanceTerritory.getTerritoryID(), rand.nextInt(l_attackingTerritory.getNumberOfArmies()), l_advanceTerritory.getOwnerPlayer());
                else
                    System.out.println("---- The neighbor does not exist for this territory or Source territory does not have Armies ----");
                return null;


            case (3):
                if (d_Player.doesCardExists("Blockade")) {
                    d_Player.removeCard("Blockade");
                    return new Blockade(d_Player, findRandomTerritory().getTerritoryID());
                } else
                    System.out.println("---- The player does not own card Blockade ----");
                return null;


            case (4):
                TerritoryDetails findOther = findOtherRandomTerritory();
                if (d_Player.doesCardExists("Airlift") && l_attackingTerritory!= null && findOther != null) {
                    d_Player.removeCard("Airlift");
                    return new Airlift(d_Player, l_attackingTerritory.getTerritoryID(), findOther.getTerritoryID(), rand.nextInt(l_attackingTerritory.getNumberOfArmies()));
                } else
                    System.out.println("---- The player does not own card Airlift or Source territory has no Armies to Move ----");
                return null;


            case (5):
                if (d_Player.doesCardExists("Diplomacy")) {
                    d_Player.removeCard("Diplomacy");
                    return new Diplomacy(d_Player, getRandomPlayer());
                } else
                    System.out.println("---- The player does not own card Diplomacy ----");
                return null;


            default:
                TerritoryDetails tmp = targetTerritoryNeighbour(l_attackingTerritory);
                if (d_Player.doesCardExists("Bomb") && l_attackingTerritory != null && tmp != null) {
                    d_Player.removeCard("Bomb");
                    return new Bomb(d_Player, tmp.getOwnerPlayer(), tmp.getTerritoryID());
                } else
                    System.out.println("---- The player does not own card Bomb ----");
                return null;
        }
    }
}