package model;


import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;


/**
 * This class generates a Player object and gives it properties.
 *
 */
public class Player  implements Serializable {

    private String d_PlayerName;
    private HashMap<String, Continent> d_OwnedContinents;
    private HashMap<String, TerritoryDetails> d_OwnedTerritories;
    private int d_OwnedArmies;
    private boolean d_isHuman;
    private order1 d_Order;
    private Queue<order1> d_OrderList;
    ArrayList<Player> d_NegotiateList;
    PlayerStrategy d_Strategy;

    ArrayList<Card> d_Deck;

    /**
     * The player is given a name by this constructor.
     *
     * @param p_playerName name of the player
     */
    public Player(String p_playerName) {
        d_PlayerName = p_playerName;
        d_OwnedContinents = new HashMap<>();
        d_OwnedTerritories = new HashMap<>();
        this.d_OwnedArmies = 0;
        d_OrderList = new ArrayDeque<>();
        d_Deck = new ArrayList<>();
        d_NegotiateList= new ArrayList<Player>();
    }

    /**
     * A getter function that yields the player's name.
     *
     * @return d_playerName name of the player
     */
    public String getPlayerName() {
        return d_PlayerName;
    }

    /**
     * Setter method for territories
     * 
     * @param p_territoryName Name of the territory
     * @param p_teritoryDetails Details of the territory
     */

    public void setOwnedTerritories(String p_territoryName, TerritoryDetails p_teritoryDetails){
        this.d_OwnedTerritories.put(p_territoryName,p_teritoryDetails);
    }

    /**
     * Getter method to go back to a player's territory.
     *
     * @return d_ownedCountries
     */
    public HashMap<String, TerritoryDetails> getOwnedTerritories() {
        return d_OwnedTerritories;
    }

    /**
     * Getter method to go back to a player's owned continents.
     *
     * @return d_ownedContinents
     */
    public HashMap<String, Continent> getOwnedContinents() {
        return d_OwnedContinents;
    }

    /**
     * To return the original army, use the getter method.
     *
     * @return d_ownedArmies
     */
    public int getOwnedArmies() {
        return d_OwnedArmies;
    }
    /**
     * Setter for User Player
     * @param d_isHuman  player is human
     */
    public void setD_isHuman(boolean d_isHuman) {
        this.d_isHuman = d_isHuman;
    }

    /**
     * Getter for User Player
     * @return true if human else false
     */
    public boolean getD_isHuman(){
        return this.d_isHuman;
    }
    /**
     * Using the setter method, the starting armies
     *
     * @param p_ownedArmies number of armies owned by players
     */
    public void setOwnedArmies(int p_ownedArmies) {
        this.d_OwnedArmies = p_ownedArmies;
    }
    public HashMap<String, TerritoryDetails> getOwnedCountries() {
        return d_OwnedTerritories;
    }

    public void flushNegotiators() {
        d_NegotiateList.clear();
    }

    public boolean issueOrder() {
        order1 order;
        order = d_Strategy.createOrder();
        if (order != null) {
            this.d_OrderList.add(order);
            return true;
        }
        return false;
    }
    /**
     * * The Order object is added by this function to the Orders list.
     * It is without parameters.
     */
    public void issue_order() {
        this.d_OrderList.add(this.d_Order);
//        for (Order l_x : d_OrderList){
//            System.out.println(l_x.getD_player().getPlayerName());
//        }
    }
    void addPlayerNegList(Player p_player) {
        d_NegotiateList.add(p_player);
    }
    /**
     * getter for order queue
     * @return d_OrderList
     */
    public Queue<order1> getD_orderList() {
        return d_OrderList;
    }

    /**
     * The produced object is set to the Players Object using this function.
     *
     * @param p_order created Order
     */
    public void addOrder(order1 p_order) {
        this.d_Order = p_order;
    }
    /**
     * Setter for Strategy
     * @param  p_strategy for Strategy
     */
    public void setStrategy(PlayerStrategy p_strategy) {
        d_Strategy = p_strategy;
    };

    /**
     * This function calls execute() on the first Order in the list.
     *
     * @return first Order in the List.
     */
    public order1 next_order() {
        return d_OrderList.poll();
    }

    /**
     * method to add card
     */
    public void addCard() {
        Card l_card = new Card();
        l_card.createCard();
        d_Deck.add(l_card);
    }

    /**
     * Added a clone of addCard inorder to test custom cards
     * @param test custom card
     */
    public void addCard(String test){
        Card l_card = new Card();
        l_card.createCard(test);
        d_Deck.add(l_card);
    }

    /**
     * If a player uses a card,it will be removed from deck of cards.
     * @param p_card String representation of card to be used
     */
    public void removeCard(String p_card)
    {
        //remove card from deck
        Iterator l_iter = d_Deck.iterator();
        while (l_iter.hasNext()) {
            Card l_card = (Card) l_iter.next();
            if (l_card.getCardType() == p_card) {
                d_Deck.remove(l_card);
                break;
            }
        }
    }

    /**
     * Before using a card, we can check if player has that card
     * @param p_card String representation of card to be used
     * @return true if card exists else false
     */
    public boolean doesCardExists(String p_card) {

        int l_existsCount = 0;
        Iterator l_iter = d_Deck.iterator();
        while (l_iter.hasNext()) {
            Card l_card = (Card) l_iter.next();
            if (l_card.getCardType() == p_card)
                l_existsCount++;
        }
        if(l_existsCount>0)
            return true;
        else
            return false;
    }

    /**
     * show the particular card owned by player
     */
    public void showCards()
    {
        Iterator l_iter = d_Deck.iterator();
        while (l_iter.hasNext()) {
            Card l_card = (Card) l_iter.next();
            System.out.println(" -------- Total cards player has : "+l_card.getCardType()+"  ---------");
        }

    }

    /**
     * Getter for Player's Cards Deck
     * @return d_Deck
     */
    public ArrayList<Card> getD_Deck() {
        return d_Deck;
    }
}