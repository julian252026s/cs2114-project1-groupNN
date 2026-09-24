package game;

import experimenting.Item;

// -------------------------------------------------------------------------
/**
 * Player class stores the player's username, inventory, and location
 * 
 *  @author Ari Bajaj
 *  @version Sep 23, 2026
 */
public class Player
{
    private String username;
    private String currentRoom;
    private Inventory inventory;
    
    // ----------------------------------------------------------
    /**
     * Create a new Player object.
     * @param user - username that the player enters
     */
    public Player(String user) {
        username = user;
        currentRoom = "Castle Courtyard";
        inventory = new Inventory();
    }

    // ----------------------------------------------------------
    /**
     * Adds an item to the player's inventory
     * @param item - an item from a Room
     * @return the name of the item was added to inventory
     */
    public String addItem(Item item) {
        inventory.addItem(item);
        return item.getName() + " added to inventory";
    }
    
    // ----------------------------------------------------------
    /**
     * Returns the name of the room the player is currently in
     * @return name of the room
     */
    public String getCurrentRoom() {
        return currentRoom;
    }
    
    // ----------------------------------------------------------
    /**
     * Updates the player's location
     * @param roomName - name of the new room the player has moved to
     */
    public void setCurrentRoom(String roomName) {
        currentRoom = roomName;
    }
    
    // ----------------------------------------------------------
    /**
     * Returns the player's chosen username
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    
    // ----------------------------------------------------------
    /**
     * Returns the player's inventory
     * @return a list of the player's inventory
     */
    public String getInventory() {
        return inventory.checkInventory();
    }
}
