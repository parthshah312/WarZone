import static org.junit.Assert.*;

import org.junit.Test;

import model.Diplomacy;
import model.Player;

public class DiplomacyTest {

    @Test
    public void testExecuteValidDiplomacy() {
        // Creating players
        Player currentPlayer = new Player("Player1");
        Player targetPlayer = new Player("Player2");

        // Creating a diplomacy order
        Diplomacy diplomacy = new Diplomacy(currentPlayer, targetPlayer);

        // Executing the diplomacy order
        assertTrue(diplomacy.execute());

        
    }
  
}
