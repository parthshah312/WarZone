import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import model.Card;

public class TestCard3 {

        @Test
    public void testCreateCardWithSpecificType() {
        Card card = new Card();
        card.createCard("Airlift");
        assertEquals("Airlift", card.d_CardType);
    }

}
