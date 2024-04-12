import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Card;

public class CardTest {

    @Test
    void RandomCardTest() {
        // Create a Card instance
        Card card = new Card();

        // Call the randomCard method
        String result = card.randomCard();

        // Verify that the result is one of the card types
        String[] expectedCardTypes = {"Bomb", "Airlift", "Blockade", "Diplomacy"};
        boolean isExpected = false;
        for (String cardType : expectedCardTypes) {
            if (result.equals(cardType)) {
                isExpected = true;
                break;
            }
        }
        assertTrue(isExpected, "The result should be one of the card types");
    }
}
