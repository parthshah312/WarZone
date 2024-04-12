import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import model.Card;

public class TestCard4 {

        @Test
    public void testRandomCard() {
        Card card = new Card();
        String cardType = card.randomCard();
        assertNotNull(cardType);
        assertTrue(cardType.equals("Bomb") || cardType.equals("Airlift") || cardType.equals("Blockade") || cardType.equals("Diplomacy"));
    }
}
