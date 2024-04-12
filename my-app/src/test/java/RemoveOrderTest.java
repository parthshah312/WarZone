
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
public class RemoveOrderTest {
    Order d_Order;
    Queue<Order> d_OrderList;
    Player d_Player;
    String d_PlayerName;

    /**
     * Set up the context
     */
    @Before
    public void before(){
        d_PlayerName = "Mir";
        d_Player = new Player(d_PlayerName);
        d_Order = new Order(d_Player,"India",5);
        d_OrderList = new ArrayDeque<>();
        d_OrderList.add(d_Order);
    }

    /**
     * Test if tests are rightly identified or not
     */
    @Test
    public void testRemoveOrder(){
        //removes order
        d_OrderList.poll();
        assertEquals(0,d_OrderList.size());
    }

}


