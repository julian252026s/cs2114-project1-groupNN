package game;
import experimenting.Item;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 *  Inventory class holds list of the items in the player's inventory
 * 
 *  @author Ari Bajaj
 *  @version Sep 23, 2026
 */
public class Inventory
{
    private ArrayList<Item> items;
    
    // ----------------------------------------------------------
    /**
     * Create a new Inventory object.
     */
    public Inventory() {
        items = new ArrayList<Item>(10);
    }

    // ----------------------------------------------------------
    /**
     * Adds an item to the inventory object
     * @param item - an item from a Room
     */
    public void addItem(Item item) {
        items.add(item);
    }
    
//    public boolean removeItem(String itemName) {
//        for(int i = 0; i < items.size(); i++) {
//            if(items[i].getName().equals(itemName)) {
//                if(items[i].equals(null)) {
//                    return false;
//                }
//                items.remove(i);
//                return true;
//            }
//        }
//    }
    
    // ----------------------------------------------------------
    /**
     * Checks if the player has all the required items to fight the dragon
     * @return true if they do, false if they don't
     */
    public boolean hasAllItems() {
        if(items.size() == 6) {
            return true;
        }
        return false;
    }
    
    // ----------------------------------------------------------
    /**
     * Returns a list of the names of the items in the inventory
     * @return returns the list of item names
     */
    public String checkInventory() {
        String str = "[";
        for(int i = 0; i < items.size(); i++) {
            str += items.get(i).getName();
            if(i != items.size() - 1) {
                str += ", ";
            }
        }
        str += "]";
        return str;
    }
}
