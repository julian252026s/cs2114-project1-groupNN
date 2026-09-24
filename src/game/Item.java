package game;

public class Item
{
    //~ Fields ................................................................
    private String name;
    private String description;
    private int weight;
    private boolean removable;

    //~ Constructors ..........................................................
    /**
     * Initializes a newly created Item object
     *
     * @param name
     *            item name
     * @param description
     *            item description
     * @param weight
     *            item weight
     */
    public Item(
        String name,
        String description,
        int weight)
    {
        super();

        this.name = name;
        this.description = description;
        this.weight = weight;
        this.removable = true;
    }

    /**
     * Initializes an Item with a default weight.
     *
     * @param name
     *            item name
     * @param description
     *            item description
     */
    public Item(
        String name,
        String description)
    {
        this(
            name,
            description,
            5);
    }

    //~Public Methods ..........................................................

    /**
     * Gets the name.
     *
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the description.
     *
     * @return description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Gets the weight.
     *
     * @return weight
     */
    public int getWeight()
    {
        return weight;
    }

    /**
     * Gets if the item is removable.
     *
     * @return removable
     */
    public boolean isRemovable()
    {
        return removable;
    }

    /**
     * Sets the removable value.
     *
     * @param value
     *            new removable value
     */
    public void setRemovable(
        boolean value)
    {
        removable = value;
    }
}