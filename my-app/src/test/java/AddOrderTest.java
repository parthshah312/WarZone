
import static org.junit.Assert.*;

import java.util.ArrayDeque;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import model.Order;
import model.Player;

/**
 * Tests if addition of orders works
 */
public class AddOrderTest {
    Order d_Order;
    Queue<Order> d_OrderList;
    Player d_Player;
    String d_PlayerName;

    /**
     * Set up the context
     */
    @Before
    public void before(){
        d_PlayerName = "Parth";
        d_Player = new Player(d_PlayerName);
        d_Order = new Order(d_Player,"Canada",4);
        d_OrderList = new ArrayDeque<>();
    }

    /**
     * Test if tests are rightly identified or not
     */
    @Test
    public void testAddOrder(){
        //adds an order
        d_OrderList.add(d_Order);
        assertEquals(d_OrderList.peek(), d_Order);
    }

}


