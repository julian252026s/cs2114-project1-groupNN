package game;

import student.TestCase;

// -------------------------------------------------------------------------
/**
 * Tests the Puzzle class
 *
 * @author Ari Bajaj
 * @version Sep 23, 2026
 */
public class PuzzleTest
    extends TestCase
{
    private Puzzle puzzle;

    public void setUp()
    {
        puzzle =
            new Puzzle(
                "What is 2 * 2?",
                "4",
                "Hint: it's 4");
    }

    public void testQetQuestion()
    {
        assertEquals(
            "What is 2 * 2?",
            puzzle.getQuestion());
    }

    public void testGetAnswer()
    {
        assertEquals(
            "4",
            puzzle.getAnswer());
    }

    public void testGetHint()
    {
        assertEquals(
            "Hint: it's 4",
            puzzle.getHint());
    }

    public void testCheckAnswer()
    {
        assertTrue(
            puzzle.checkAnswer("4"));

        assertFalse(
            puzzle.checkAnswer("5"));
    }

    public void testIsSolved()
    {
        assertFalse(
            puzzle.isSolved());

        puzzle.checkAnswer("4");

        assertTrue(
            puzzle.isSolved());
    }
}