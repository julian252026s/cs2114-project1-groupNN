package game;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the ProjectGame class.
 *
 * @author Ash Satasia
 * @version Sep 24, 2026
 */
public class ProjectGameTest
    extends TestCase
{
    private ProjectGame game;
    private Puzzle puzzle;
    private Player player;

    // ----------------------------------------------------------
    /**
     * Sets up the objects before each test.
     */
    public void setUp()
    {
        game = new ProjectGame();

        puzzle = new Puzzle(
            "What is 2 + 2?",
            "4",
            "Hint: add 2 and 2");

        player = new Player("Ash");
    }

    // ----------------------------------------------------------
    /**
     * Tests valid commands.
     */
    public void testValidateCommand()
    {
        assertEquals(
            "FORWARD",
            game.validateCommand("forward"));

        assertEquals(
            "SOLVE",
            game.validateCommand("solve"));

        assertEquals(
            "HINT",
            game.validateCommand("hint"));

        assertEquals(
            "FIGHT",
            game.validateCommand("fight"));
    }

    // ----------------------------------------------------------
    /**
     * Tests invalid commands.
     */
    public void testInvalidCommand()
    {
        assertNull(
            game.validateCommand("jump"));

        assertNull(
            game.validateCommand(""));

        assertNull(
            game.validateCommand(null));
    }

    // ----------------------------------------------------------
    /**
     * Tests solving a puzzle correctly.
     */
    public void testSolvePuzzleCorrect()
    {
        assertEquals(
            "You have solved the puzzle!",
            game.solvePuzzle(puzzle, "4"));

        assertTrue(
            puzzle.isSolved());
    }

    // ----------------------------------------------------------
    /**
     * Tests solving a puzzle incorrectly.
     */
    public void testSolvePuzzleIncorrect()
    {
        assertEquals(
            "Incorrect answer. Enter HINT if you would like a hint.",
            game.solvePuzzle(puzzle, "5"));

        assertFalse(
            puzzle.isSolved());
    }

    // ----------------------------------------------------------
    /**
     * Tests solving a null puzzle.
     */
    public void testSolveNullPuzzle()
    {
        assertEquals(
            "There is no puzzle to solve.",
            game.solvePuzzle(null, "4"));
    }

    // ----------------------------------------------------------
    /**
     * Tests entering an empty puzzle answer.
     */
    public void testEmptyPuzzleAnswer()
    {
        assertEquals(
            "Please enter an answer.",
            game.solvePuzzle(puzzle, ""));
    }

    // ----------------------------------------------------------
    /**
     * Tests trying to solve an already solved puzzle.
     */
    public void testAlreadySolvedPuzzle()
    {
        puzzle.checkAnswer("4");

        assertEquals(
            "This puzzle has already been solved.",
            game.solvePuzzle(puzzle, "4"));
    }

    // ----------------------------------------------------------
    /**
     * Tests getting a puzzle hint.
     */
    public void testGetHint()
    {
        assertEquals(
            "Hint: add 2 and 2",
            game.getHint(puzzle));
    }

    // ----------------------------------------------------------
    /**
     * Tests getting a hint when there is no puzzle.
     */
    public void testGetHintNull()
    {
        assertEquals(
            "There is no puzzle here.",
            game.getHint(null));
    }

    // ----------------------------------------------------------
    /**
     * Tests getting a hint after the puzzle is solved.
     */
    public void testGetHintSolved()
    {
        puzzle.checkAnswer("4");

        assertEquals(
            "This puzzle has already been solved.",
            game.getHint(puzzle));
    }

    // ----------------------------------------------------------
    /**
     * Tests a player without all required items.
     */
    public void testMissingItems()
    {
        assertFalse(
            game.hasAllRequiredItems(player));
    }

    // ----------------------------------------------------------
    /**
     * Tests a null player.
     */
    public void testNullPlayerItems()
    {
        assertFalse(
            game.hasAllRequiredItems(null));
    }

    // ----------------------------------------------------------
    /**
     * Tests fighting when the player is not in the lair.
     */
    public void testFightWrongRoom()
    {
        assertEquals(
            "You are not in the right place. "
                + "Find the Dragon's Lair to fight.",
            game.fightDragon(
                player,
                "Castle Courtyard"));
    }

    // ----------------------------------------------------------
    /**
     * Tests fighting without all required items.
     */
    public void testFightMissingItems()
    {
        assertEquals(
            "You do not have all the items needed "
                + "to fight the dragon.",
            game.fightDragon(
                player,
                "Dragon's Lair"));
    }

    // ----------------------------------------------------------
    /**
     * Tests fighting with a null player.
     */
    public void testFightNullPlayer()
    {
        assertEquals(
            "There is no player.",
            game.fightDragon(
                null,
                "Dragon's Lair"));
    }

    // ----------------------------------------------------------
    /**
     * Tests the help message.
     */
    public void testShowHelp()
    {
        String help = game.showHelp();

        assertTrue(
            help.contains("FORWARD"));

        assertTrue(
            help.contains("TAKE"));

        assertTrue(
            help.contains("SOLVE"));

        assertTrue(
            help.contains("FIGHT"));
    }

    // ----------------------------------------------------------
    /**
     * Tests movement commands.
     */
    public void testMovementCommands()
    {
        assertTrue(
            game.isMovementCommand("FORWARD"));

        assertTrue(
            game.isMovementCommand("back"));

        assertTrue(
            game.isMovementCommand("LEFT"));

        assertTrue(
            game.isMovementCommand("right"));

        assertFalse(
            game.isMovementCommand("fight"));

        assertFalse(
            game.isMovementCommand(null));
    }

    // ----------------------------------------------------------
    /**
     * Tests getting the argument after a command.
     */
    public void testGetArgument()
    {
        assertEquals(
            "Sword",
            game.getArgument("TAKE Sword"));

        assertEquals(
            "Dungeon Key",
            game.getArgument("TAKE Dungeon Key"));

        assertEquals(
            "84",
            game.getArgument("SOLVE 84"));
    }

    // ----------------------------------------------------------
    /**
     * Tests input with no argument.
     */
    public void testNoArgument()
    {
        assertEquals(
            "",
            game.getArgument("TAKE"));

        assertEquals(
            "",
            game.getArgument(null));
    }

    // ----------------------------------------------------------
    /**
     * Tests having all six required items without changing
     * the existing Player or Inventory classes.
     */
    public void testHasAllRequiredItems()
    {
        Player fullPlayer = new Player("Ash")
        {
            @Override
            public String getInventory()
            {
                return
                    "[Dungeon Key, Enchantment Room Key, "
                    + "Strength Potion, Spell Book, Sword, Shield]";
            }
        };

        assertTrue(
            game.hasAllRequiredItems(fullPlayer));
    }

    // ----------------------------------------------------------
    /**
     * Tests winning the dragon fight.
     */
    public void testFightDragonWin()
    {
        Player fullPlayer = new Player("Ash")
        {
            @Override
            public String getInventory()
            {
                return
                    "[Dungeon Key, Enchantment Room Key, "
                    + "Strength Potion, Spell Book, Sword, Shield]";
            }
        };

        assertEquals(
            "Congratulations! You have defeated the dragon!",
            game.fightDragon(
                fullPlayer,
                "Dragon's Lair"));
    }
}