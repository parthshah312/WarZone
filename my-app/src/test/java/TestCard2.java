import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import model.Card;

public class TestCard2 {
    
    @Test
    public void testCreateCard() {
        Card card = new Card();
        card.createCard();
        String cardType = card.d_CardType;
        assertNotNull(cardType);
        assertTrue(cardType.equals("Bomb") || cardType.equals("Airlift") || cardType.equals("Blockade") || cardType.equals("Diplomacy"));
    }
}
