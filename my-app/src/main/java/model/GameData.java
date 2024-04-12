package model;

import java.io.Serializable;
import java.util.ArrayList;

import enumClass.GamePhase;

public class GameData extends Observable implements Serializable {

    private GameMap d_Map;
    private String d_MapType;
    private GamePhase d_GamePhase;
    private ArrayList<Player> d_Players;
    private Player d_ActivePlayer;
    private Card d_Card;
    private int d_CardsDetails;
    private GamePhases d_Phase;
    private String d_PhaseName;
    public GamePhases getD_Phase() {
        return d_Phase;
    }
    public void setD_Phase(GamePhases d_Phase) {
        this.d_Phase = d_Phase;
    }
    /**
     * consturctor
     */
    public GameData(){
        d_Map = new GameMap();
        d_GamePhase = GamePhase.NULL;
        d_Players = new ArrayList<Player>();
        d_ActivePlayer = null;
        d_Card = new Card();
        d_CardsDetails = 0;
    }

    /**
     * parameterized constructor
     * @param p_map game map
     * @param p_mapType type of map
     * @param p_gamePhase phase of game
     * @param p_phase phases of game
     * @param p_players list of players
     * @param p_activePlayer active player
     * @param p_card card
     * @param p_phaseName name of phase
     */
    public GameData(GameMap p_map, String p_mapType, GamePhase p_gamePhase,GamePhases p_phase, ArrayList<Player> p_players, Player p_activePlayer, Card p_card,String p_phaseName){
        this.d_Map = p_map;
        this.d_MapType = p_mapType;
        this.d_GamePhase = p_gamePhase;
        this.d_Phase=p_phase;
        this.d_Players = p_players;
        this.d_ActivePlayer = p_activePlayer;
        this.d_Card = p_card;
        this.d_PhaseName = p_phaseName;
    }

    /**
     * getter method for map
     * @return game map
     */
    public GameMap getMap() {
        return this.d_Map;
    }

    /**
     * setter method for map
     * @param p_map game map
     */
    public void setMap(GameMap p_map) {
        this.d_Map = p_map;
    }

    /**
     * getter method for map type
     * @return type of map
     */
    public String getMapType() {
        return d_MapType;
    }

    /**
     * setter method for map type
     * @param p_mapType type of map
     */
    public void setMapType(String p_mapType) {
        this.d_MapType = p_mapType;
    }

    /**
     * getter method for game phase
     * @return phase of game
     */
    public GamePhase getGamePhase() {
        return this.d_GamePhase;
    }


    /**
     * setter method for game phase
     * @param p_gamePhase phase of game
     */
    public void setGamePhase(GamePhase p_gamePhase) {
        this.d_GamePhase = p_gamePhase;
        if(this.d_GamePhase==GamePhase.ASSIGN_REINFORCEMENTS){
            System.out.println("---- "+this.d_ActivePlayer.getPlayerName() + "'s reinforcement phase is begin ----");
            notifyObservers(this);
        } else if (this.d_GamePhase == GamePhase.ISSUE_ORDERS) {
            System.out.println("---- "+this.d_ActivePlayer.getPlayerName() + "'s Issue order phase is begin ----");
            notifyObservers(this);
        }
    }

    /**
     * getter method for players
     * @return list of players
     */
    public ArrayList<Player> getPlayers() {
        return this.d_Players;
    }

    /**
     * setter method for players
     * @param p_players list of players
     */
    public void setPlayers(ArrayList<Player> p_players) {
        this.d_Players = p_players;
    }

    /**
     * getter method for active player
     * @return active player
     */
    public Player getActivePlayer(){
        return this.d_ActivePlayer;
    }

    /**
     * setter method for active player
     * @param p_player player
     */
    public void setActivePlayer(Player p_player){
        this.d_ActivePlayer = p_player;
        if(p_player!=null){
            notifyObservers(this);
        }
    }

    /**
     * setter method for phase
     * @param p_phaseName name of phase
     */
    public void setD_PhaseName(String p_phaseName){
        this.d_PhaseName = p_phaseName;
    }

    /**
     * getter method for phase
     * @return name of phase
     */
    public String getD_PhaseName() {
        return d_PhaseName;
    }

    /**
     * getter method for deck
     * @return deck
     */
    public Card getDeck() {
        return d_Card;
    }

    /**
     * setter method for deck
     * @param p_card card
     */
    public void setDeck(Card p_card) {
        this.d_Card = p_card;
    }

    /**
     * getter method for card
     * @return total cards
     */
    public int getCardsDealt() {
        return d_CardsDetails;
    }

    /**
     * setter methods for cards dealt
     * @param p_cardsDealt number of cards dealt
     */
    public void setCardsDealt(int  p_cardsDealt) {
        this.d_CardsDetails = p_cardsDealt;
    }

    /**
     * method to remove player
     * @param p_p player
     */
    public void removePlayer(Player p_p){
        this.d_Players.remove(p_p);
    }

    /**
     * method to reset game data
     */
    public void resetGameData(){
        d_Map = new GameMap();
        d_GamePhase = GamePhase.NULL;
        d_Players = new ArrayList<Player>();
        d_ActivePlayer = null;
        d_Card = new Card();
        d_CardsDetails = 0;
    }
}
