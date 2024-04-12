import static org.junit.Assert.*;

import model.GameMap;
import model.GamePhases;
import model.Player;
import org.junit.Test;
import java.util.ArrayList;

public class TestGamePhase {

    // Create a concrete subclass for testing purposes
    private static class GamePhaseTest extends GamePhases {
        @Override
        public boolean assignCountries(GameMap p_gamemap, ArrayList<Player> p_gameplayers) {
            return false;
        }

        @Override
        public void loadMap(String[] p_data, String p_map) {
        }

        @Override
        public void showMap(GameMap p_gamemap) {
        }

        @Override
        public void editMap(String[] p_data, String p_gamemap) {
        }

        @Override
        public void savemap(String[] p_data, String p_gamemap) {
        }

        @Override
        public void editNeighbour(String[] p_data, String p_countryId, String p_neighborCountryId) {
        }

        @Override
        public void editCountry(String[] p_data, String p_continentId, String p_countryId) {
        }

        @Override
        public void editContinent(String[] p_data, String p_continentId, int p_controlValue) {
        }

        @Override
        public void reinforce() {
        }

        @Override
        public void gamePlayer(String[] p_data, ArrayList<Player> p_gameplayers, String p_playerName) {
        }

        @Override
        public void showMap(ArrayList<Player> p_gameplayers, GameMap p_gamemap) {
        }

        @Override
        public void validatemap() {
        }
    }
@Test
public void testPrintInvalidCommandMessage() {
    GamePhaseTest testGamePhase = new TestGamePhase.GamePhaseTest();
    String phaseName = "TestPhase";
    testGamePhase.d_PhaseName = phaseName;
    assertEquals(phaseName, testGamePhase.getD_PhaseName());
    System.out.println("Invalid command in state " + testGamePhase.getD_PhaseName());
}
}
