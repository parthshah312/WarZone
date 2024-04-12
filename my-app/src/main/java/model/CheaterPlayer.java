package model;

import java.util.HashMap;

public class CheaterPlayer extends PlayerStrategy{
    Player d_OtherPlayer;
    public CheaterPlayer(Player p_player, GameMap p_map) {
        super(p_player, p_map);
    }

    
    @Override
    public order1 createOrder() {

        this.d_Player.setOwnedArmies(0);

        HashMap<String, TerritoryDetails> l_territoryList = new HashMap<String, TerritoryDetails>();
        for(String l_s : this.d_Player.getOwnedTerritories().keySet()){
            l_territoryList.put(l_s, this.d_Player.getOwnedTerritories().get(l_s));
        }

        for(TerritoryDetails l_territories : l_territoryList.values()){
            for(TerritoryDetails l_neighbours : l_territories.getNeighbours().values()){
                if(!this.d_Player.getOwnedTerritories().containsKey(l_neighbours.getTerritoryID().toLowerCase())) {
                    d_OtherPlayer= l_neighbours.getOwnerPlayer();
                    this.d_Player.getOwnedTerritories().put(l_neighbours.getTerritoryID().toLowerCase(),l_neighbours);
                    d_OtherPlayer.getOwnedTerritories().remove(l_neighbours.getTerritoryID().toLowerCase());
                    d_Player.addCard();
                    l_neighbours.setOwnerPlayer(this.d_Player);
                }
            }
        }

        for(TerritoryDetails l_newTerritories : this.d_Player.getOwnedTerritories().values()){
            for(TerritoryDetails l_newneighbours : l_newTerritories.getNeighbours().values()){
                if(!(this.d_Player.getOwnedTerritories().containsKey(l_newneighbours.getTerritoryID().toLowerCase()))) {
                    l_newTerritories.setNumberOfArmies(l_newTerritories.getNumberOfArmies() * 2);
                }
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

    @Override
    protected TerritoryDetails toMoveFrom() {
        return null;
    }


}
