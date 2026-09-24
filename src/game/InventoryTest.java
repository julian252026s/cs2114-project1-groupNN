package game;

import experimenting.Item;
import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the Inventory class
 * 
 * @author Ari Bajaj
 * @version Sep 23, 2026
 */
public class InventoryTest
    extends TestCase
{
    private Inventory inv;
    private Item item;

    public void setUp()
    {
        inv = new Inventory();
        item = new Item("Item 1", "First item");
        inv.addItem(item);
    }


    // ----------------------------------------------------------
    /**
     * Tests the checkInventory() method
     */
    public void testCheckInventory()
    {
        Item item2 = new Item("Item 2", "Second item");
        inv.addItem(item2);
        assertEquals("[Item 1, Item 2]", inv.checkInventory());
    }


    // ----------------------------------------------------------
    /**
     * Tests the addItem() method
     */
    public void testAddItem()
    {
        assertEquals("[Item 1]", inv.checkInventory());
    }


    // ----------------------------------------------------------
    /**
     * Tests the hasAllItems() method
     */
    public void testHasAllItems()
    {
        inv.addItem(item);
        inv.addItem(item);
        assertFalse(inv.hasAllItems());
        inv.addItem(item);
        assertTrue(inv.hasAllItems());
    }


    public void testRemoveItem()
    {
        assertTrue(inv.removeItem("Item 1"));
        assertFalse(inv.removeItem("Item 2"));
    }

}
