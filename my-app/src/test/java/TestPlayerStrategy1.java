import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.GameMap;
import model.Order;
import model.Player;
import model.PlayerStrategy;
import model.TerritoryDetails;
import model.order1;

public class TestPlayerStrategy1 {
    Player mockPlayer;
    GameMap mockMap;
    TestPlayerStrategyConcrete testPlayerStrategy;
    @Before
    public void setUp() {
        mockPlayer = new Player("MockPlayer");
        mockMap = new GameMap("MockMap");
        testPlayerStrategy = new TestPlayerStrategyConcrete(mockPlayer, mockMap);
    }

    @Test
    public void testToAttackFrom() {
        TerritoryDetails territory = testPlayerStrategy.toAttackFrom();
        assertNotNull(territory);
    }

    private class TestPlayerStrategyConcrete extends PlayerStrategy {
        public TestPlayerStrategyConcrete(Player player, GameMap map) {
            super(player, map);
        }

        @Override
        public order1 createOrder() {
            return new Order(mockPlayer, "territory1", 5);
        }

        @Override
        protected TerritoryDetails toAttackFrom() {
            return new TerritoryDetails();
        }

        @Override
        protected TerritoryDetails toAttack() {
            return new TerritoryDetails();
        }

        @Override
        protected TerritoryDetails toMoveFrom() {
            return new TerritoryDetails();
        }
    }
}
