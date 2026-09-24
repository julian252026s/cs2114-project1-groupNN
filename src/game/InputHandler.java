package game;

import java.util.HashSet;
import java.util.Set;

// -------------------------------------------------------------------------
/**
 * InputHandler class handles all player input and validates it to work with the
 * rest of the game code
 *
 * @author Ari Bajaj
 * @version Sep 23, 2026
 */
public class InputHandler
{
    private final Set<String> allowedCommands;

    // ----------------------------------------------------------
    /**
     * Create a new InputHandler object with the allowed commands in the game
     */
    public InputHandler()
    {
        allowedCommands =
            new HashSet<>();

        allowedCommands.add("FORWARD");
        allowedCommands.add("QUIT");
        allowedCommands.add("INVENTORY");
        allowedCommands.add("TAKE");
        allowedCommands.add("HELP");
        allowedCommands.add("BACK");
        allowedCommands.add("LEFT");
        allowedCommands.add("RIGHT");
        allowedCommands.add("LOOK");
        allowedCommands.add("SOLVE");
        allowedCommands.add("FIGHT");
        allowedCommands.add("HINT");
    }

    // ----------------------------------------------------------
    /**
     * Validates user input to match the allowed commands in the game
     *
     * @param input
     *            - a command that the player inputs
     * @return the validated command
     */
    public String validateCommand(
        String input)
    {
        if (input == null
            || input.trim().isEmpty())
        {
            return null;
        }

        String normalizedInput =
            input.trim().toUpperCase();

        String commandWord =
            normalizedInput
                .split("\\s+")[0];

        if (isValidCommand(commandWord))
        {
            return commandWord;
        }

        return null;
    }

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     *
     * @param command
     *            - the validated command
     * @return if the player entered a proper command
     */
    public boolean isValidCommand(
        String command)
    {
        if (command == null)
        {
            return false;
        }

        return allowedCommands.contains(
            command.toUpperCase());
    }
}