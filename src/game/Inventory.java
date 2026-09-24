package game;

import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * Inventory class holds list of the items in the player's inventory
 *
 * @author Ari Bajaj
 * @version Sep 23, 2026
 */
public class Inventory
{
    private ArrayList<Item> items;

    // ----------------------------------------------------------
    /**
     * Create a new Inventory object.
     */
    public Inventory()
    {
        items =
            new ArrayList<Item>();
    }

    // ----------------------------------------------------------
    /**
     * Adds an item to the inventory object
     *
     * @param item
     *            - an item from a Room
     */
    public void addItem(Item item)
    {
        if (item != null)
        {
            items.add(item);
        }
    }

    // ----------------------------------------------------------
    /**
     * Removes an item from inventory
     *
     * @param itemName
     *            - item to be removed
     * @return true if removed, false if not
     */
    public boolean removeItem(
        String itemName)
    {
        if (itemName == null)
        {
            return false;
        }

        for (int i = 0;
             i < items.size();
             i++)
        {
            if (items
                .get(i)
                .getName()
                .equalsIgnoreCase(itemName))
            {
                items.remove(i);

                return true;
            }
        }

        return false;
    }

    // ----------------------------------------------------------
    /**
     * Checks if the player has all the required items
     * to fight the dragon
     *
     * @return true if they do, false if they don't
     */
    public boolean hasAllItems()
    {
        return items.size() >= 6;
    }

    // ----------------------------------------------------------
    /**
     * Checks for one item.
     *
     * @param itemName
     *            item being searched for
     * @return true if inventory contains the item
     */
    public boolean hasItem(
        String itemName)
    {
        if (itemName == null)
        {
            return false;
        }

        for (Item item : items)
        {
            if (item
                .getName()
                .equalsIgnoreCase(itemName))
            {
                return true;
            }
        }

        return false;
    }

    // ----------------------------------------------------------
    /**
     * Returns a list of the names of the items in the inventory
     *
     * @return returns the list of item names
     */
    public String checkInventory()
    {
        String str = "[";

        for (int i = 0;
             i < items.size();
             i++)
        {
            str +=
                items.get(i).getName();

            if (i != items.size() - 1)
            {
                str += ", ";
            }
        }

        str += "]";

        return str;
    }
}