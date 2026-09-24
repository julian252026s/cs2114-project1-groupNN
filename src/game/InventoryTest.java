package game;

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
        inv =
            new Inventory();

        item =
            new Item(
                "Item 1",
                "First item");

        inv.addItem(item);
    }

    public void testCheckInventory()
    {
        Item item2 =
            new Item(
                "Item 2",
                "Second item");

        inv.addItem(item2);

        assertEquals(
            "[Item 1, Item 2]",
            inv.checkInventory());
    }

    public void testAddItem()
    {
        assertEquals(
            "[Item 1]",
            inv.checkInventory());
    }

    public void testHasAllItems()
    {
        inv.addItem(
            new Item(
                "Item 2",
                "Second"));

        inv.addItem(
            new Item(
                "Item 3",
                "Third"));

        inv.addItem(
            new Item(
                "Item 4",
                "Fourth"));

        inv.addItem(
            new Item(
                "Item 5",
                "Fifth"));

        assertFalse(
            inv.hasAllItems());

        inv.addItem(
            new Item(
                "Item 6",
                "Sixth"));

        assertTrue(
            inv.hasAllItems());
    }

    public void testRemoveItem()
    {
        assertTrue(
            inv.removeItem("Item 1"));

        assertFalse(
            inv.removeItem("Item 2"));
    }
}