package model;

import java.io.Serializable;
import java.util.ArrayList;

import enumClass.GamePhase;
public class GameDataBuilder implements Serializable {

    private GameMap d_Map;
    private String d_MapType;
    private GamePhase d_GamePhase;
    private GamePhases d_Phase;
    private ArrayList<Player> d_Players;
    private Player d_ActivePlayer;
    private Card d_Card;
    private int d_CardsDetails;
    private String d_PhaseName;

    /**
     * setter method for map
     * @param p_map game map
     * @return game map
     */
    public GameDataBuilder setMap(GameMap p_map) {
        this.d_Map = p_map;
        return this;
    }

    /**
     * setter method for map type
     * @param p_mapType type of map
     * @return map type
     */
    public GameDataBuilder setMapType(String p_mapType) {
        this.d_MapType = p_mapType;
        return this;
    }

    /**
     * setter method for game phase
     * @param p_gamePhase phase of game
     * @return game phase
     */
    public GameDataBuilder setGamePhase(GamePhase p_gamePhase) {
        this.d_GamePhase = p_gamePhase;
        return this;
    }

    /**
     * setter method for phases
     * @param p_Phase phases of game
     * @return game phases
     */
    public GameDataBuilder setPhase(GamePhases p_Phase) {
        this.d_Phase = p_Phase;
        return this;
    }

    /**
     * setter method for players
     * @param p_players list of players
     * @return player list
     */
    public GameDataBuilder setPlayers(ArrayList<Player> p_players) {
        this.d_Players = p_players;
        return this;
    }

    /**
     * setter method for active player
     * @param p_activePlayer active player
     * @return active player
     */
    public GameDataBuilder setActivePlayer(Player p_activePlayer) {
        this.d_ActivePlayer = p_activePlayer;
        return this;
    }

    /**
     * setter method for deck
     * @param p_card card
     * @return card
     */
    public GameDataBuilder setDeck(Card p_card) {
        this.d_Card = p_card;
        return this;
    }

    /**
     * setter method for cards dealt
     * @param p_cardsDealt number of cards dealt
     * @return total cards dealt
     */
    public GameDataBuilder setCardsDealt(int p_cardsDealt) {
        this.d_CardsDetails = p_cardsDealt;
        return this;
    }

    /**
     * setter method for phase name
     * @param p_phaseName name of phase
     * @return phase name
     */
    public GameDataBuilder setPhaseName(String p_phaseName)
    {
        this.d_PhaseName = p_phaseName;
        return this;
    }

    /**
     * method to build game data 
     * @return game data
     */
    public GameData buildGameData(){
        return new GameData(d_Map, d_MapType, d_GamePhase,d_Phase, d_Players, d_ActivePlayer, d_Card,d_PhaseName);
    }
}