package game;

import experimenting.Item;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the Player class
 * 
 * @author Ari Bajaj
 * @version Sep 23, 2026
 */
public class PlayerTest
    extends TestCase
{
    private Player user;
    private Item item;

    public void setUp()
    {
        user = new Player("ari");
        item = new Item("Item 1", "First item");
        user.addItem(item);
    }


    // ----------------------------------------------------------
    /**
     * Tests the addItem() method
     */
    public void testAddItem()
    {
        assertEquals("Item 1 added to inventory", user.addItem(item));
    }


    // ----------------------------------------------------------
    /**
     * Tests the getUsername() method
     */
    public void testGetUsername()
    {
        assertEquals("ari", user.getUsername());
    }


    // ----------------------------------------------------------
    /**
     * Tests the getInventory() method
     */
    public void testGetInventory()
    {
        assertEquals("[Item 1]", user.getInventory());
    }

}
