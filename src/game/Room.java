package game;

import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 * Represents one room in the castle.
 */
public class Room
{
    private String name;
    private String description;

    private Room forward;
    private Room back;
    private Room left;
    private Room right;

    private ArrayList<Item> items;
    private Puzzle puzzle;

    // ----------------------------------------------------------
    /**
     * Creates a room without a puzzle.
     *
     * @param name
     *            room name
     * @param description
     *            room description
     */
    public Room(
        String name,
        String description)
    {
        this(
            name,
            description,
            null);
    }

    // ----------------------------------------------------------
    /**
     * Creates a room with a puzzle.
     *
     * @param name
     *            room name
     * @param description
     *            room description
     * @param puzzle
     *            room puzzle
     */
    public Room(
        String name,
        String description,
        Puzzle puzzle)
    {
        this.name = name;
        this.description = description;
        this.puzzle = puzzle;

        items =
            new ArrayList<Item>();
    }

    // ----------------------------------------------------------
    /**
     * Gets the room name.
     *
     * @return room name
     */
    public String getName()
    {
        return name;
    }

    // ----------------------------------------------------------
    /**
     * Gets the description.
     *
     * @return description
     */
    public String getDescription()
    {
        return description;
    }

    // ----------------------------------------------------------
    /**
     * Gets an exit.
     *
     * @param direction
     *            requested direction
     * @return connected room or null
     */
    public Room getExit(String direction)
    {
        if (direction == null)
        {
            return null;
        }

        switch (direction
            .trim()
            .toUpperCase())
        {
            case "FORWARD":
                return forward;

            case "BACK":
                return back;

            case "LEFT":
                return left;

            case "RIGHT":
                return right;

            default:
                return null;
        }
    }

    // ----------------------------------------------------------
    /**
     * Sets a room exit.
     *
     * @param direction
     *            direction
     * @param room
     *            connected room
     */
    public void setExit(
        String direction,
        Room room)
    {
        if (direction == null)
        {
            return;
        }

        switch (direction
            .trim()
            .toUpperCase())
        {
            case "FORWARD":
                forward = room;
                break;

            case "BACK":
                back = room;
                break;

            case "LEFT":
                left = room;
                break;

            case "RIGHT":
                right = room;
                break;

            default:
                break;
        }
    }

    // ----------------------------------------------------------
    /**
     * Adds an item.
     *
     * @param item
     *            item being added
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
     * Removes an item.
     *
     * @param itemName
     *            item name
     * @return removed item or null
     */
    public Item removeItem(
        String itemName)
    {
        if (itemName == null)
        {
            return null;
        }

        for (int i = 0;
             i < items.size();
             i++)
        {
            Item item =
                items.get(i);

            if (item
                .getName()
                .equalsIgnoreCase(itemName))
            {
                items.remove(i);

                return item;
            }
        }

        return null;
    }

    // ----------------------------------------------------------
    /**
     * Checks for an unsolved puzzle.
     *
     * @return true if puzzle exists and is unsolved
     */
    public boolean hasPuzzle()
    {
        return puzzle != null
            && !puzzle.isSolved();
    }

    // ----------------------------------------------------------
    /**
     * Gets the puzzle.
     *
     * @return puzzle
     */
    public Puzzle getPuzzle()
    {
        return puzzle;
    }

    // ----------------------------------------------------------
    /**
     * Lists the room items.
     *
     * @return item names
     */
    public String getItems()
    {
        String result = "[";

        for (int i = 0;
             i < items.size();
             i++)
        {
            result +=
                items.get(i).getName();

            if (i < items.size() - 1)
            {
                result += ", ";
            }
        }

        result += "]";

        return result;
    }
}