package game;

import java.util.HashSet;
import java.util.Set;

// -------------------------------------------------------------------------
/**
 * Adds the extra game commands without changing InputHandler.
 *
 * @author Ash Satasia
 * @version Sep 24, 2026
 */
public class ExtendedInputHandler
    extends InputHandler
{
    private Set<String> extraCommands;

    // ----------------------------------------------------------
    /**
     * Creates a new ExtendedInputHandler.
     */
    public ExtendedInputHandler()
    {
        super();

        extraCommands = new HashSet<String>();

        extraCommands.add("LOOK");
        extraCommands.add("SOLVE");
        extraCommands.add("HINT");
        extraCommands.add("FIGHT");
    }

    // ----------------------------------------------------------
    /**
     * Checks whether a command is valid.
     *
     * @param command command entered by player
     * @return true if command is valid
     */
    @Override
    public boolean isValidCommand(String command)
    {
        if (command == null)
        {
            return false;
        }

        String normalized = command.trim().toUpperCase();

        return super.isValidCommand(normalized)
            || extraCommands.contains(normalized);
    }

    // ----------------------------------------------------------
    /**
     * Validates and normalizes player input.
     *
     * @param input player input
     * @return valid command or null
     */
    @Override
    public String validateCommand(String input)
    {
        if (input == null || input.trim().isEmpty())
        {
            return null;
        }

        String normalized = input.trim().toUpperCase();

        String command =
            normalized.split("\\s+")[0];

        if (isValidCommand(command))
        {
            return command;
        }

        return null;
    }
}