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
     * @param name 
     * @param description 
     * @param weight 
     */
    public Item(String name, String description, int weight)
    {
        super();
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.removable = true;
    }
    //~Public  Methods ........................................................
    /**
     * Gets the name 
     * @return name for the string
     */
    public String getName()
    {
        return name;
    }
    /**
     * Gets the description used
     * @return description
     */
    public String getDescription()
    {
        return description;
    }
    /**
     * Gets the weight
     * @return weight
     */
    public int getWeight()
    {
        return weight;
    }
    /**
     * Gets if the boolean is removable
     * @return removable object
     */
    public boolean isRemovable()
    {
        return removable;
    }
    
    /**
     * gets the removable value
     * @param value
     */
    public void setRemovable(boolean value)
    {
        removable = value;
    }
}