import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Player;

public class RemoveCardTest {

    @Test
    void testRemoveCard() {
        // Create a Deck instance
        Player deck = new Player("Parth");


        deck.addCard("Bomb");
        deck.addCard("Airlift");

        // Remove card1 from the deck
        deck.removeCard("Bomb");

        // Verify that the deck no longer contains card1
        assertFalse(deck.doesCardExists("Bomb"), "The deck should not contain card1");
        assertTrue(deck.doesCardExists("Airlift"), "The deck should still contain card2");
    }
}
