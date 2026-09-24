package game;

// -------------------------------------------------------------------------
/**
 * Player class stores the player's username, inventory, and location
 *
 * @author Ari Bajaj
 * @version Sep 23, 2026
 */
public class Player
{
    private String username;
    private Inventory inventory;

    // ----------------------------------------------------------
    /**
     * Create a new Player object.
     *
     * @param user
     *            - username that the player enters
     */
    public Player(String user)
    {
        username = user;
        inventory = new Inventory();
    }

    // ----------------------------------------------------------
    /**
     * Adds an item to the player's inventory
     *
     * @param item
     *            - an item from a Room
     * @return the name of the item was added to inventory
     */
    public String addItem(Item item)
    {
        if (item == null)
        {
            return "Item could not be added to inventory";
        }

        inventory.addItem(item);

        return item.getName()
            + " added to inventory";
    }

    // ----------------------------------------------------------
    /**
     * Returns the player's chosen username
     *
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    // ----------------------------------------------------------
    /**
     * Returns the player's inventory
     *
     * @return a list of the player's inventory
     */
    public String getInventory()
    {
        return inventory.checkInventory();
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
        return inventory.hasAllItems();
    }

    // ----------------------------------------------------------
    /**
     * Checks if the player has an item.
     *
     * @param itemName
     *            item name
     * @return true if player has item
     */
    public boolean hasItem(
        String itemName)
    {
        return inventory.hasItem(itemName);
    }
}