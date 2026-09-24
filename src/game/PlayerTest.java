package game;

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
        user =
            new Player("ari");

        item =
            new Item(
                "Item 1",
                "First item");

        user.addItem(item);
    }

    public void testAddItem()
    {
        assertEquals(
            "Item 1 added to inventory",
            user.addItem(item));
    }

    public void testGetUsername()
    {
        assertEquals(
            "ari",
            user.getUsername());
    }

    public void testGetInventory()
    {
        assertEquals(
            "[Item 1]",
            user.getInventory());
    }

    public void testHasAllItems()
    {
        assertFalse(
            user.hasAllItems());

        user.addItem(
            new Item(
                "Item 2",
                "Second"));

        user.addItem(
            new Item(
                "Item 3",
                "Third"));

        user.addItem(
            new Item(
                "Item 4",
                "Fourth"));

        user.addItem(
            new Item(
                "Item 5",
                "Fifth"));

        user.addItem(
            new Item(
                "Item 6",
                "Sixth"));

        assertTrue(
            user.hasAllItems());
    }
}