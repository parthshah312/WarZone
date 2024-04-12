import static org.junit.Assert.*;

import org.junit.Test;

import model.Card;

public class TestCard {

    @Test
    public void testGetCardType() {
        Card card = new Card("Bomb");
        assertEquals("Bomb", card.d_CardType);
    }
}
