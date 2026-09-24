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
        inputHandler =
            new ExtendedInputHandler();
    }

    // ----------------------------------------------------------
    /**
     * Validates a command.
     *
     * @param input
     *            player input
     * @return command or null
     */
    public String validateCommand(
        String input)
    {
        return inputHandler
            .validateCommand(input);
    }

    // ----------------------------------------------------------
    /**
     * Attempts to solve a puzzle.
     *
     * @param puzzle
     *            puzzle
     * @param answer
     *            answer
     * @return result
     */
    public String solvePuzzle(
        Puzzle puzzle,
        String answer)
    {
        if (puzzle == null)
        {
            return
                "There is no puzzle to solve.";
        }

        if (puzzle.isSolved())
        {
            return
                "This puzzle has already been solved.";
        }

        if (answer == null
            || answer.trim().isEmpty())
        {
            return
                "Please enter an answer.";
        }

        if (puzzle.checkAnswer(
            answer.trim()))
        {
            return
                "You have solved the puzzle!";
        }

        return
            "Incorrect answer. Enter HINT if you would like a hint.";
    }

    // ----------------------------------------------------------
    /**
     * Gets a puzzle hint.
     *
     * @param puzzle
     *            puzzle
     * @return hint
     */
    public String getHint(
        Puzzle puzzle)
    {
        if (puzzle == null)
        {
            return
                "There is no puzzle here.";
        }

        if (puzzle.isSolved())
        {
            return
                "This puzzle has already been solved.";
        }

        return puzzle.getHint();
    }

    // ----------------------------------------------------------
    /**
     * Checks all required items.
     *
     * @param player
     *            player
     * @return true if all items are owned
     */
    public boolean hasAllRequiredItems(
        Player player)
    {
        if (player == null)
        {
            return false;
        }

        return player.hasItem("Dungeon Key")
            && player.hasItem(
                "Enchantment Room Key")
            && player.hasItem(
                "Strength Potion")
            && player.hasItem(
                "Spell Book")
            && player.hasItem("Sword")
            && player.hasItem("Shield");
    }

    // ----------------------------------------------------------
    /**
     * Handles a dragon fight.
     *
     * @param player
     *            player
     * @param currentRoom
     *            room name
     * @return result
     */
    public String fightDragon(
        Player player,
        String currentRoom)
    {
        if (player == null)
        {
            return
                "There is no player.";
        }

        if (currentRoom == null
            || !currentRoom.equals(
                "Dragon's Lair"))
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
     * Returns help.
     *
     * @return help string
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
     * Checks a movement command.
     *
     * @param command
     *            command
     * @return true if movement
     */
    public boolean isMovementCommand(
        String command)
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
     * Gets text after the first command.
     *
     * @param input
     *            input
     * @return argument
     */
    public String getArgument(
        String input)
    {
        if (input == null)
        {
            return "";
        }

        String trimmed =
            input.trim();

        int space =
            trimmed.indexOf(' ');

        if (space == -1)
        {
            return "";
        }

        return trimmed
            .substring(space + 1)
            .trim();
    }
}