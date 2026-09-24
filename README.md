# cs2114-project1-groupNN
Small Text Adventure Game
<<<<<<< HEAD

public class YourName {
    public static void main(String[] args) {
        System.out.println("Your Julian is on the team.");
    }
}
public class YourName {
    public static void main(String[] args) {
        System.out.println("Ash.");
    }
}
<<<<<<< Updated upstream
public class YourName {
    public static void main(String[] args) {
        System.out.println("Elizabeth.");
    }
}
>>>>>>> a1f40f24d71b3eb585e3c79d4d2a956ffab9d656
public class AdventureGame
    extends Game
{
    //~ Fields ................................................................

    //~ Constructors ..........................................................
    /**
     * Initializes the newly created AdventureGame object.
     */
    public AdventureGame()
    {
        super(new Adventurer(), new Parser());
    }
    
    //~Public  Methods ........................................................
    /**
     * Creates the welcome message
     * @return message
     */
    @Override
    public String welcomeMessage()
    {
        return
            "Welcome to Castle of Nightmares !\n"
            + "Do everything you can to go room to room, solve riddles,\n"
            + "and pick up all the items required to slay the Dragon.\n  "
            + "Type 'help' if you need help to see the available commands.";
    }
    /**
     * Creates all the command thats will be used in the game
     */
    @Override
    public void createCommands()
    {
        CommandWords commands = parser().commandWords();
        commands.addCommand("go",   new GoCommand());
        commands.addCommand("help", new HelpCommand(commands));
        commands.addCommand("quit", new QuitCommand());
        commands.addCommand("north", new DirectionCommand("north"));
        commands.addCommand("n",     new DirectionCommand("north"));
        commands.addCommand("south", new DirectionCommand("south"));
        commands.addCommand("s",     new DirectionCommand("south"));
        commands.addCommand("east",  new DirectionCommand("east"));
        commands.addCommand("e",     new DirectionCommand("east"));
        commands.addCommand("west",  new DirectionCommand("west"));
        commands.addCommand("w",     new DirectionCommand("west"));
        commands.addCommand("up",    new DirectionCommand("up"));
        commands.addCommand("u",     new DirectionCommand("up"));
        commands.addCommand("down",  new DirectionCommand("down"));
        commands.addCommand("d",     new DirectionCommand("down"));
        commands.addCommand("take",      new TakeCommand());
        commands.addCommand("drop",      new DropCommand());
        commands.addCommand("inventory", new InventoryCommand());
        commands.addCommand("i",         new InventoryCommand());
        commands.addCommand("back", new BackCommand());
        commands.addCommand("victory", new VictoryAtLast());
    }
    
    @Override
    public void createRooms()
    {
        // create the rooms
        Location castleEntrance = new Location("Castle Entrance", "You stand in a dark "
            + "stone foyer behind a set of heavy oak doors. "
            + "Old tapestries hang on the walls, and a cold brazier sits in the center."
            + " A doorway to the north leads further inside.");
        Location courtyard = new Location("Courtyard", "You enter an open stone courtyard"
            + " with a dry fountain in the middle. "
            + "Weeds cover the stone paths, and the night air is cold and quiet. Dark "
            + "archways lead to different parts of the castle.");
        Location enchantmentRoom = new Location("Enchantment Room", "This circular room"
            + " has shelves filled with books and glass jars. "
            + "Purple runes glow on the dark floor, casting a soft light around the space."
            + " A flat stone altar stands in the center.");
        Location dungeon = new Location("Dungeon", "You descend into a cold room lined"
            + " with rusted cell bars. "
            + "The air is damp, and old wooden stocks rest against the back wall. Next"
            + " to the stocks sits a small locked iron box.");
        Location armory = new Location("Armory", "Racks of old swords and shields hang"
            + " along the stone walls. "
            + "Large wooden chests sit on the floor, and the room smells like rust and"
            + " leather. A suit of metal armor stands in the center.");
        Location dragonsLair = new Location("Dragon's Lair", "You enter a huge stone"
            + " cave filled with hot steam. "
            + "A large pile of gold coins and treasure rests in the center of the room."
            + " A red dragon sleeps on top of the pile.");
        
        courtyard.addItems(new Item("Dungeon Key", "An old iron key with a sharp tip. "
            + "It feels heavy and opens the dungeon door.", 5));
        courtyard.addItems(new Item("Enchantment Room Key", "A strange key that"
            + " glows with magic. "
            + "It unlocks the enchantment room.", 5));
        dungeon.addItems(new Item("Strength Potion", "A small glass vial filled with red"
            + " liquid. "
            + "It makes you feel stronger when consumed.", 5));
        enchantmentRoom.addItems(new Item("SpellBook", "A thick leather book filled with"
            + " glowing spells. "
            + "It holds powerful ancient magic.", 5));
        armory.addItems(new Item("Sword", "A sharp steel blade with a wrapped handle. "
            + "It is well suited for close combat.", 5));
        armory.addItems(new Item("Shield", "A sturdy metal shield with a wooden backing. "
            + "It helps block incoming attacks.", 5));
        
        // initialize room exits
        castleEntrance.setExit("north", courtyard);
        
        courtyard.setExit("east", dungeon);
        courtyard.setExit("west", enchantmentRoom);
        
        dungeon.setExit("north",armory );
        dungeon.setExit("south", courtyard);
        
        enchantmentRoom.setExit("south", courtyard);
        enchantmentRoom.setExit("north", armory);

        armory.setExit("north", dragonsLair);
        

        Adventurer adv = (Adventurer) player();
        adv.trackRoom(castleEntrance);
        adv.trackRoom(courtyard);
        adv.trackRoom(dungeon);
        adv.trackRoom(enchantmentRoom);
        adv.trackRoom(armory);
        adv.trackRoom(dragonsLair);
       
        // the player starts the game outside
        player().setCurrentRoom(castleEntrance);
        
    }
}





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
