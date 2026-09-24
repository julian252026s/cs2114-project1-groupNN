package game;

import java.util.Scanner;

public class Game
{
    private Player player;
    private Room entrance;
    private Room courtyard;
    private Room greatHall;
    private Room dungeon;
    private Room enchantment;
    private Room armory;
    private Room lair;

    private Puzzle puzzle1;
    private Puzzle puzzle2;
    private Puzzle puzzle3;

    private Scanner scanner;
    private InputHandler inputHandler;

    private boolean isRunning;
    private Room currentRoom;
    private Room checkpoint;

    // ----------------------------------------------------------
    /**
     * Creates the game.
     */
    public Game()
    {
        scanner = new Scanner(System.in);
        inputHandler = new InputHandler();
        createGame();
    }

    // ----------------------------------------------------------
    /**
     * Creates all rooms, puzzles, items, and exits.
     */
    private void createGame()
    {
        puzzle1 = new Puzzle(
            "What is the next number in the following pattern: "
                + "2, 4, 6, 8, ...",
            "10",
            "Hint: add 2");

        puzzle2 = new Puzzle(
            "What is the next number in the following pattern: "
                + "3, 1, 5, 3, 7, ...",
            "5",
            "Hint: down 2 up 4");

        puzzle3 = new Puzzle(
            "What is the next number in the following pattern: "
                + "4, 12, 10, 30, 28, ...",
            "84",
            "Hint: times 3 minus 2");

        entrance = new Room(
            "Castle Entrance",
            "You stand inside the dark entrance of an abandoned castle.",
            puzzle1);

        courtyard = new Room(
            "Castle Courtyard",
            "An open courtyard sits in the center of the castle.",
            puzzle2);

        greatHall = new Room(
            "Great Hall",
            "A large hall connects the different parts of the castle.");

        enchantment = new Room(
            "Enchantment Room",
            "Purple runes glow across the floor and magical books "
                + "line the walls.");

        dungeon = new Room(
            "Dungeon",
            "The dungeon is cold and dark with rusted cells along "
                + "the walls.");

        armory = new Room(
            "Armory",
            "Old weapons and armor fill the room.",
            puzzle3);

        lair = new Room(
            "Dragon's Lair",
            "A huge dragon sleeps on a pile of treasure.");

        // Room exits
        entrance.setExit("FORWARD", courtyard);

        courtyard.setExit("BACK", entrance);
        courtyard.setExit("FORWARD", greatHall);

        greatHall.setExit("BACK", courtyard);
        greatHall.setExit("LEFT", enchantment);
        greatHall.setExit("RIGHT", dungeon);
        greatHall.setExit("FORWARD", armory);

        enchantment.setExit("RIGHT", greatHall);

        dungeon.setExit("LEFT", greatHall);

        armory.setExit("BACK", greatHall);
        armory.setExit("FORWARD", lair);

        lair.setExit("BACK", armory);

        // Items
        courtyard.addItem(
            new Item(
                "Enchantment Room Key",
                "Key for the Enchantment Room"));

        courtyard.addItem(
            new Item(
                "Dungeon Key",
                "Key for the Dungeon"));

        dungeon.addItem(
            new Item(
                "Strength Potion",
                "A purple potion that gives you strength"));

        enchantment.addItem(
            new Item(
                "Spell Book",
                "An old, dusty book full of useful spells"));

        armory.addItem(
            new Item(
                "Shield",
                "A dented metal shield"));

        armory.addItem(
            new Item(
                "Sword",
                "A dazzling, two-handed longsword"));

        currentRoom = entrance;
        checkpoint = entrance;
    }

    // ----------------------------------------------------------
    /**
     * Starts the game.
     */
    public void startGame()
    {
        System.out.println(
            "Welcome to How to Slay Your Dragon! "
                + "Please enter a username: ");

        String user = scanner.nextLine();

        while (user == null || user.trim().isEmpty())
        {
            System.out.println(
                "Please enter a valid username with "
                    + "characters or numbers: ");

            user = scanner.nextLine();
        }

        player = new Player(user.trim());

        isRunning = true;

        while (isRunning)
        {
            System.out.println();

            System.out.println(
                "You are currently in the "
                    + currentRoom.getName());

            System.out.println(
                currentRoom.getDescription());

            if (currentRoom == lair)
            {
                System.out.println(
                    "You have reached the Dragon's Lair! "
                        + "Enter FIGHT to battle the dragon.");
            }
            else
            {
                System.out.println(
                    "What would you like to do?");
            }

            String input = scanner.nextLine();

            processCommand(input);
        }
    }

    // ----------------------------------------------------------
    /**
     * Processes the user's command.
     *
     * @param input
     *            player input
     */
    public void processCommand(String input)
    {
        String command =
            inputHandler.validateCommand(input);

        if (command == null)
        {
            System.out.println(
                "Invalid action! Type HELP to see valid commands.");

            return;
        }

        String argument =
            getArgument(input);

        switch (command)
        {
            case "QUIT":
                System.out.println(
                    "Thanks for playing. Goodbye!");

                isRunning = false;
                break;

            case "HELP":
                showHelp();
                break;

            case "INVENTORY":
                System.out.println(
                    "You are carrying: "
                        + player.getInventory());

                break;

            case "LOOK":
                look();
                break;

            case "FORWARD":
            case "BACK":
            case "LEFT":
            case "RIGHT":
                move(command);
                break;

            case "TAKE":
                takeItem(argument);
                break;

            case "SOLVE":
                solvePuzzle(argument);
                break;

            case "HINT":
                showHint();
                break;

            case "FIGHT":
                fightDragon();
                break;

            default:
                System.out.println(
                    "Invalid action!");
                break;
        }
    }

    // ----------------------------------------------------------
    /**
     * Moves the player.
     *
     * @param direction
     *            movement direction
     */
    private void move(String direction)
    {
        if (currentRoom.hasPuzzle()
            && direction.equals("FORWARD"))
        {
            System.out.println(
                "You must solve the puzzle before moving forward.");

            System.out.println(
                currentRoom.getPuzzle().getQuestion());

            return;
        }

        Room nextRoom =
            currentRoom.getExit(direction);

        if (nextRoom == null)
        {
            System.out.println(
                "There is no exit in that direction.");

            return;
        }

        checkpoint = currentRoom;
        currentRoom = nextRoom;
    }

    // ----------------------------------------------------------
    /**
     * Takes an item from the room.
     *
     * @param itemName
     *            name of item
     */
    private void takeItem(String itemName)
    {
        if (itemName == null
            || itemName.isEmpty())
        {
            System.out.println(
                "Type TAKE followed by the item name.");

            return;
        }

        if (currentRoom.hasPuzzle())
        {
            System.out.println(
                "You must solve the puzzle before taking items here.");

            return;
        }

        Item item =
            currentRoom.removeItem(itemName);

        if (item == null)
        {
            System.out.println(
                "That item is not in this room.");

            return;
        }

        System.out.println(
            player.addItem(item));
    }

    // ----------------------------------------------------------
    /**
     * Shows the current room.
     */
    private void look()
    {
        System.out.println(
            currentRoom.getDescription());

        System.out.println(
            "Items: "
                + currentRoom.getItems());

        if (currentRoom.hasPuzzle())
        {
            System.out.println(
                "Puzzle: "
                    + currentRoom
                        .getPuzzle()
                        .getQuestion());
        }

        System.out.print(
            "Exits: ");

        if (currentRoom.getExit("FORWARD") != null)
        {
            System.out.print(
                "FORWARD ");
        }

        if (currentRoom.getExit("BACK") != null)
        {
            System.out.print(
                "BACK ");
        }

        if (currentRoom.getExit("LEFT") != null)
        {
            System.out.print(
                "LEFT ");
        }

        if (currentRoom.getExit("RIGHT") != null)
        {
            System.out.print(
                "RIGHT ");
        }

        System.out.println();
    }

    // ----------------------------------------------------------
    /**
     * Solves the current puzzle.
     *
     * @param answer
     *            player's answer
     */
    private void solvePuzzle(String answer)
    {
        if (!currentRoom.hasPuzzle())
        {
            System.out.println(
                "There is no unsolved puzzle in this room.");

            return;
        }

        if (answer == null
            || answer.isEmpty())
        {
            System.out.println(
                currentRoom
                    .getPuzzle()
                    .getQuestion());

            System.out.print(
                "Answer: ");

            answer = scanner.nextLine();
        }

        if (currentRoom
            .getPuzzle()
            .checkAnswer(answer))
        {
            System.out.println(
                "You have solved the puzzle!");
        }
        else
        {
            System.out.println(
                "Incorrect answer. Enter HINT "
                    + "if you would like a hint.");
        }
    }

    // ----------------------------------------------------------
    /**
     * Displays a puzzle hint.
     */
    private void showHint()
    {
        if (!currentRoom.hasPuzzle())
        {
            System.out.println(
                "There is no unsolved puzzle in this room.");

            return;
        }

        System.out.println(
            currentRoom
                .getPuzzle()
                .getHint());
    }

    // ----------------------------------------------------------
    /**
     * Handles the dragon fight.
     */
    private void fightDragon()
    {
        if (currentRoom != lair)
        {
            System.out.println(
                "You are not in the right place. "
                    + "Find the Dragon's Lair to fight.");

            return;
        }

        if (!player.hasAllItems())
        {
            System.out.println(
                "You do not have all the items needed "
                    + "to fight the dragon.");

            System.out.println(
                "You lost the fight and returned "
                    + "to your last checkpoint.");

            currentRoom = checkpoint;

            return;
        }

        System.out.println(
            "Congratulations! You have defeated the dragon!");

        isRunning = false;
    }

    // ----------------------------------------------------------
    /**
     * Shows the available commands.
     */
    private void showHelp()
    {
        System.out.println(
            "Here are your available actions: "
                + "LOOK, FORWARD, BACK, LEFT, RIGHT, "
                + "TAKE item, INVENTORY, HELP, QUIT, "
                + "SOLVE answer, HINT, FIGHT");
    }

    // ----------------------------------------------------------
    /**
     * Gets the argument following a command.
     *
     * @param input
     *            full input
     * @return argument
     */
    private String getArgument(String input)
    {
        if (input == null)
        {
            return "";
        }

        String trimmed =
            input.trim();

        int space =
            trimmed.indexOf(' ');

        if (space < 0)
        {
            return "";
        }

        return trimmed
            .substring(space + 1)
            .trim();
    }

    // ----------------------------------------------------------
    /**
     * Runs the game.
     *
     * @param args
     *            command line arguments
     */
    public static void main(String[] args)
    {
        Game game =
            new Game();

        game.startGame();
    }
}