package model;

public abstract class PlayerStrategy {
    GameMap d_Map;
    Player d_Player;
    protected PlayerStrategy(Player p_player, GameMap p_map){
        d_Player = p_player;
        d_Map = p_map;
    }
    public abstract order1 createOrder();
    protected abstract TerritoryDetails toAttackFrom();
    protected abstract TerritoryDetails toAttack();
    protected abstract TerritoryDetails toMoveFrom();
}
