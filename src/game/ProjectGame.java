package game;

// -------------------------------------------------------------------------
/**
 * Provides additional game functionality and integrates
 * the existing Player, Puzzle, and InputHandler classes.
 *
 * @author Ash Satasia
 * @version Sep 24, 2026
 */
public class ProjectGame
{
    private ExtendedInputHandler inputHandler;

    // ----------------------------------------------------------
    /**
     * Creates a new ProjectGame object.
     */
    public ProjectGame()
    {
        inputHandler = new ExtendedInputHandler();
    }

    // ----------------------------------------------------------
    /**
     * Validates a command entered by the player.
     *
     * @param input player input
     * @return validated command or null
     */
    public String validateCommand(String input)
    {
        return inputHandler.validateCommand(input);
    }

    // ----------------------------------------------------------
    /**
     * Solves a puzzle using the player's answer.
     *
     * @param puzzle puzzle being solved
     * @param answer player's answer
     * @return result message
     */
    public String solvePuzzle(Puzzle puzzle, String answer)
    {
        if (puzzle == null)
        {
            return "There is no puzzle to solve.";
        }

        if (puzzle.isSolved())
        {
            return "This puzzle has already been solved.";
        }

        if (answer == null || answer.trim().isEmpty())
        {
            return "Please enter an answer.";
        }

        if (puzzle.checkAnswer(answer.trim()))
        {
            return "You have solved the puzzle!";
        }

        return "Incorrect answer. Enter HINT if you would like a hint.";
    }

    // ----------------------------------------------------------
    /**
     * Gets the hint for a puzzle.
     *
     * @param puzzle current puzzle
     * @return puzzle hint
     */
    public String getHint(Puzzle puzzle)
    {
        if (puzzle == null)
        {
            return "There is no puzzle here.";
        }

        if (puzzle.isSolved())
        {
            return "This puzzle has already been solved.";
        }

        return puzzle.getHint();
    }

    // ----------------------------------------------------------
    /**
     * Checks whether the player has every required item.
     *
     * @param player current player
     * @return true if player has all six items
     */
    public boolean hasAllRequiredItems(Player player)
    {
        if (player == null)
        {
            return false;
        }

        String inventory = player.getInventory();

        return inventory.contains("Dungeon Key")
            && inventory.contains("Enchantment Room Key")
            && inventory.contains("Strength Potion")
            && inventory.contains("Spell Book")
            && inventory.contains("Sword")
            && inventory.contains("Shield");
    }

    // ----------------------------------------------------------
    /**
     * Handles the dragon fight.
     *
     * @param player current player
     * @param currentRoom name of player's current room
     * @return result of the fight
     */
    public String fightDragon(
        Player player,
        String currentRoom)
    {
        if (player == null)
        {
            return "There is no player.";
        }

        if (currentRoom == null
            || !currentRoom.equals("Dragon's Lair"))
        {
            return
                "You are not in the right place. "
                + "Find the Dragon's Lair to fight.";
        }

        if (!hasAllRequiredItems(player))
        {
            return
                "You do not have all the items needed "
                + "to fight the dragon.";
        }

        return
            "Congratulations! You have defeated the dragon!";
    }

    // ----------------------------------------------------------
    /**
     * Returns the available game commands.
     *
     * @return help message
     */
    public String showHelp()
    {
        return
            "Available actions: "
            + "FORWARD, BACK, LEFT, RIGHT, "
            + "LOOK, TAKE, INVENTORY, SOLVE, "
            + "HINT, FIGHT, HELP, QUIT";
    }

    // ----------------------------------------------------------
    /**
     * Checks whether a command is a movement command.
     *
     * @param command command being checked
     * @return true for movement command
     */
    public boolean isMovementCommand(String command)
    {
        if (command == null)
        {
            return false;
        }

        return command.equalsIgnoreCase("FORWARD")
            || command.equalsIgnoreCase("BACK")
            || command.equalsIgnoreCase("LEFT")
            || command.equalsIgnoreCase("RIGHT");
    }

    // ----------------------------------------------------------
    /**
     * Gets the text following a command.
     *
     * Example:
     * TAKE Sword returns Sword.
     *
     * @param input complete player input
     * @return command argument
     */
    public String getArgument(String input)
    {
        if (input == null)
        {
            return "";
        }

        String trimmed = input.trim();

        int space =
            trimmed.indexOf(' ');

        if (space == -1)
        {
            return "";
        }

        return trimmed.substring(space + 1).trim();
    }
}