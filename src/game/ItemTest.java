package game;

import student.TestCase;

public class ItemTest
    extends TestCase
{
    private Item item;

    public void setUp()
    {
        item =
            new Item(
                "Sword",
                "A sharp sword",
                5);
    }

    public void testGetName()
    {
        assertEquals(
            "Sword",
            item.getName());
    }

    public void testGetDescription()
    {
        assertEquals(
            "A sharp sword",
            item.getDescription());
    }

    public void testGetWeight()
    {
        assertEquals(
            5,
            item.getWeight());
    }

    public void testRemovable()
    {
        assertTrue(
            item.isRemovable());

        item.setRemovable(false);

        assertFalse(
            item.isRemovable());
    }

    public void testTwoArgumentConstructor()
    {
        Item other =
            new Item(
                "Shield",
                "A metal shield");

        assertEquals(
            "Shield",
            other.getName());

        assertEquals(
            5,
            other.getWeight());
    }
}