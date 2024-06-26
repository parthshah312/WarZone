package model;

import java.util.Random;
/**
 * Class handles implementation related to cards.
 */
public class Card {

    /**
     * Represents type of Card from Bomb, Airlift, Blockade and Diplomacy
     */
    public String d_CardType;

    /**
     * This list contains the cards issued to players
     */
    String[] d_CardsList = {"Bomb","Airlift","Blockade","Diplomacy"};

    /**
     *The method gives type of a card.
     * @return string card type
     */
    public String getCardType()
    {
        return d_CardType;
    }

    /**
     * Default constructor of Card to access the methods of this class.
     */
    public Card(){
    }

    /**
     * This constructor will assign type of cards
     * @param p_cardType Card Type that is assigned
     */
    public Card(String p_cardType){
        this.d_CardType=p_cardType;
    }
    /**
     * Stores the random card picked in the CardType String
     */
    public void createCard()
    {
        d_CardType = randomCard();
    }

    /**
     * Stores the temp card picked in the CardType String
     * @param temp specific card
     */
    public void createCard(String temp) { d_CardType = temp; }
    /**
     * Picks a random card from the Cards List using random generator
     * @return The index of the Cards List
     */
    public String randomCard()
    {
        Random randomGenerator = new Random();
        int index = randomGenerator.nextInt(d_CardsList.length);
        return d_CardsList[index];
    }

}
