package game;

import java.util.Scanner;

public class Game
{
    private Player player;
    private Room entrance;
    private Room courtyard;
    private Room dungeon;
    private Room enchantment;
    private Room armory;
    private Room lair;
    private Inventory armoryItems;
    private Inventory courtyardItems;
    private Inventory dungeonItems;
    private Inventory enchantmentItems;
    private Item key1;
    private Item key2;
    private Item potion;
    private Item shield;
    private Item sword;
    private Item book;
    private Puzzle puzzle1;
    private Puzzle puzzle2;
    private Puzzle puzzle3;
    private Scanner scanner;

    // ----------------------------------------------------------
    /**
     * Runs the game loop
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        // initializes all the items, rooms, and puzzles
        scanner = new Scanner(System.in);
        key1 = new Item("Enchantment Room Key", "Key for the Enchantment Room");
        key2 = new Item("Dungeon Key", "Key for the Dungeon");
        potion = new Item(
            "Strength Potion",
            "A purple potion that gives you strength");
        shield = new Item("Shield", "A dented metal shield");
        sword = new Item("Sword", "A dazzling, two-handed longsword");
        book =
            new Item("Spell Book", "An old, dusty book full of useful spells");

        armoryItems = new Inventory();
        armoryItems.addItem(sword);
        armoryItems.addItem(shield);
        courtyardItems = new Inventory();
        courtyardItems.addItem(key1);
        courtyardItems.addItem(key2);
        dungeonItems = new Inventory();
        dungeonItems.addItem(potion);
        enchantmentItems = new Inventory();
        enchantmentItems.addItem(book);

        puzzle1 = new Puzzle(
            "What is the next number in the following pattern: 2, 4, 6, 8, ...",
            "10",
            "Hint: add 2");
        puzzle2 = new Puzzle(
            "What is the next number in the following pattern: 3, 1, 5, 3, 7, ...",
            "5",
            "Hint: down 2 up 4");
        puzzle3 = new Puzzle(
            "What is the next number in the following pattern: 4, 12, 10, 30, 28, ...",
            "84",
            "Hint: times 3 minus 2");

        entrance = new Room(
            "Castle Entrance",
            courtyard,
            null,
            null,
            null,
            null,
            puzzle1); // Have to solve the puzzle to access the next room
        courtyard = new Room(
            "Castle Courtyard",
            null,
            entrance,
            enchantment,
            dungeon,
            courtyardItems,
            puzzle2); // Have to solve the puzzle to access the keys
        enchantment = new Room(
            "Enchanment Room",
            armory,
            null,
            null,
            courtyard,
            enchantmentItems,
            null);
        dungeon = new Room(
            "Dungeon",
            armory,
            null,
            courtyard,
            null,
            dungeonItems,
            null);
        armory = new Room(
            "Armory",
            lair,
            null,
            enchantment,
            dungeon,
            armoryItems,
            puzzle3); // Have to solve the armory puzzle to access the items
        lair = new Room("Dragon's Lair", null, armory, null, null, null, null);

        // Initializes player
        boolean isRunning = true;
        System.out.println(
            "Welcome to How to Slay Your Dragon! Please enter a username: ");
        String user = scanner.nextLine();
        while (user.trim().isEmpty() || user == null)
        {
            System.out.println(
                "Please enter a valid username with characters or numbers: ");
            user = scanner.nextLine();
        }
        player = new Player(user);
        Room currentRoom = entrance;

        // Starts running game loop
        while (isRunning)
        {
            System.out
                .println("You are currently in the " + currentRoom.getName());
            if (currentRoom == lair)
            {
                System.out.println(
                    "You have reached the Dragon's lair! Enter fight to battle the dragon: ");
            }
            else
            {
                System.out.println("What would you like to do? ");
            }
            String input = scanner.nextLine();
            String command = inputHandler.validateCommand(input);
            if (command == null)
            {
                System.out.println(
                    "Invalid action! Type 'HELP' to see valid commands.");
                continue;
            }
            switch (command)
            {
                case "QUIT":
                    System.out.println("Thanks for playing. Goodbye!");
                    isRunning = false;
                    break;
                case "HELP":
                    System.out.println(
                        "Here are your available actions: LOOK, FORWARD, BACK, LEFT, RIGHT, TAKE, INVENTORY, HELP, QUIT, SOLVE, FIGHT");
                    break;
                case "INVENTORY":
                    System.out
                        .println("You are carrying: " + player.getInventory());
                    break;
                case "FORWARD":
                    if (currentRoom.getExit("FORWARD"))
                    {
                        currentRoom = currentRoom.getExit("FORWARD");
                        break;
                    }
                    else
                    {
                        System.out
                            .println("There is no exit in that direction");
                        break;
                    }
                case "BACK":
                    if (currentRoom.getExit("BACK"))
                    {
                        currentRoom = currentRoom.getExit("BACK");
                        break;
                    }
                    else
                    {
                        System.out
                            .println("There is no exit in that direction");
                        break;
                    }
                case "LEFT":
                    if (currentRoom.getExit("LEFT"))
                    {
                        currentRoom = currentRoom.getExit("LEFT");
                        break;
                    }
                    else
                    {
                        System.out
                            .println("There is no exit in that direction");
                        break;
                    }
                case "RIGHT":
                    if (currentRoom.getExit("RIGHT"))
                    {
                        currentRoom = currentRoom.getExit("RIGHT");
                        break;
                    }
                    else
                    {
                        System.out
                            .println("There is no exit in that direction");
                        break;
                    }
                case "TAKE":
                    // if currentRoom has an item, add to inventory. if not, say
                    // there is no items to take
                case "LOOK":
                    // Report if the currentRoom has any items, puzzles, or
                    // exits
                case "SOLVE":
                    if (currentRoom.hasPuzzle())
                    {
                        System.out
                            .println(currentRoom.getPuzzle().getQuestion());
                        String answer = scanner.nextLine();
                        boolean solved = currentRoom.getPuzzle().checkAnswer();
                        if (solved)
                        {
                            System.out.println("You have solved the puzzle!");
                            break;
                        }
                        else
                        {
                            System.out.println(
                                "Incorrect answer. Enter HINT if you would like a hint.");
                            break;
                        }
                    }
                case "HINT":
                    if (!currentRoom.getPuzzle().isSolved())
                    {
                        System.out.println(currentRoom.getPuzzle().getHint());
                    }
                case "FIGHT":
                    if (!player.hasAllItems())
                    {
                        System.out.println(
                            "You do not have all the items needed to fight the dragon.");
                        break;
                    }
                    else if (currentRoom != lair)
                    {
                        System.out.println(
                            "You are not in the right place. Find the dragon's lair to fight.");
                        break;
                    }
                    else
                    {
                        // battle ensues
                        System.out.println(
                            "Congratulations! You have defeated the dragon!");
                        isRunning = false;
                        break;
                    }
            }

        }

    }

}
