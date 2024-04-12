import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Player;

public class AddCardTest {

    @Test
    void testAddCard() {
        Player deck = new Player("Parth");

        // Add a card to the deck
        deck.addCard();

        // Verify that the deck contains one card
        assertEquals(1, deck.getD_Deck().size(), "The deck should contain one card");
    }
}
