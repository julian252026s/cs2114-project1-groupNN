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

    public void setUp()
    {
        game =
            new ProjectGame();

        puzzle =
            new Puzzle(
                "What is 2 + 2?",
                "4",
                "Hint: add 2 and 2");

        player =
            new Player("Ash");
    }

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

    public void testInvalidCommand()
    {
        assertNull(
            game.validateCommand("jump"));

        assertNull(
            game.validateCommand(""));

        assertNull(
            game.validateCommand(null));
    }

    public void testSolvePuzzleCorrect()
    {
        assertEquals(
            "You have solved the puzzle!",
            game.solvePuzzle(
                puzzle,
                "4"));

        assertTrue(
            puzzle.isSolved());
    }

    public void testSolvePuzzleIncorrect()
    {
        assertEquals(
            "Incorrect answer. Enter HINT if you would like a hint.",
            game.solvePuzzle(
                puzzle,
                "5"));

        assertFalse(
            puzzle.isSolved());
    }

    public void testSolveNullPuzzle()
    {
        assertEquals(
            "There is no puzzle to solve.",
            game.solvePuzzle(
                null,
                "4"));
    }

    public void testEmptyPuzzleAnswer()
    {
        assertEquals(
            "Please enter an answer.",
            game.solvePuzzle(
                puzzle,
                ""));
    }

    public void testAlreadySolvedPuzzle()
    {
        puzzle.checkAnswer("4");

        assertEquals(
            "This puzzle has already been solved.",
            game.solvePuzzle(
                puzzle,
                "4"));
    }

    public void testGetHint()
    {
        assertEquals(
            "Hint: add 2 and 2",
            game.getHint(puzzle));
    }

    public void testGetHintNull()
    {
        assertEquals(
            "There is no puzzle here.",
            game.getHint(null));
    }

    public void testGetHintSolved()
    {
        puzzle.checkAnswer("4");

        assertEquals(
            "This puzzle has already been solved.",
            game.getHint(puzzle));
    }

    public void testMissingItems()
    {
        assertFalse(
            game.hasAllRequiredItems(
                player));
    }

    public void testNullPlayerItems()
    {
        assertFalse(
            game.hasAllRequiredItems(
                null));
    }

    public void testFightWrongRoom()
    {
        assertEquals(
            "You are not in the right place. "
                + "Find the Dragon's Lair to fight.",
            game.fightDragon(
                player,
                "Castle Courtyard"));
    }

    public void testFightMissingItems()
    {
        assertEquals(
            "You do not have all the items needed "
                + "to fight the dragon.",
            game.fightDragon(
                player,
                "Dragon's Lair"));
    }

    public void testFightNullPlayer()
    {
        assertEquals(
            "There is no player.",
            game.fightDragon(
                null,
                "Dragon's Lair"));
    }

    public void testShowHelp()
    {
        String help =
            game.showHelp();

        assertTrue(
            help.contains("FORWARD"));

        assertTrue(
            help.contains("TAKE"));

        assertTrue(
            help.contains("SOLVE"));

        assertTrue(
            help.contains("FIGHT"));
    }

    public void testMovementCommands()
    {
        assertTrue(
            game.isMovementCommand(
                "FORWARD"));

        assertTrue(
            game.isMovementCommand(
                "back"));

        assertTrue(
            game.isMovementCommand(
                "LEFT"));

        assertTrue(
            game.isMovementCommand(
                "right"));

        assertFalse(
            game.isMovementCommand(
                "fight"));

        assertFalse(
            game.isMovementCommand(
                null));
    }

    public void testGetArgument()
    {
        assertEquals(
            "Sword",
            game.getArgument(
                "TAKE Sword"));

        assertEquals(
            "Dungeon Key",
            game.getArgument(
                "TAKE Dungeon Key"));

        assertEquals(
            "84",
            game.getArgument(
                "SOLVE 84"));
    }

    public void testNoArgument()
    {
        assertEquals(
            "",
            game.getArgument(
                "TAKE"));

        assertEquals(
            "",
            game.getArgument(
                null));
    }

    public void testHasAllRequiredItems()
    {
        addAllRequiredItems(
            player);

        assertTrue(
            game.hasAllRequiredItems(
                player));
    }

    public void testFightDragonWin()
    {
        addAllRequiredItems(
            player);

        assertEquals(
            "Congratulations! You have defeated the dragon!",
            game.fightDragon(
                player,
                "Dragon's Lair"));
    }

    private void addAllRequiredItems(
        Player target)
    {
        target.addItem(
            new Item(
                "Dungeon Key",
                "Key"));

        target.addItem(
            new Item(
                "Enchantment Room Key",
                "Key"));

        target.addItem(
            new Item(
                "Strength Potion",
                "Potion"));

        target.addItem(
            new Item(
                "Spell Book",
                "Book"));

        target.addItem(
            new Item(
                "Sword",
                "Sword"));

        target.addItem(
            new Item(
                "Shield",
                "Shield"));
    }
}