package game;

// -------------------------------------------------------------------------
/**
 * Puzzle class holds the puzzles in the Rooms that players need to solve
 *
 * @author Ari Bajaj
 * @version Sep 23, 2026
 */
public class Puzzle
{
    private String question;
    private String answer;
    private String hint;
    private boolean solved;

    // ----------------------------------------------------------
    /**
     * Create a new Puzzle object.
     *
     * @param question
     *            - the puzzle question
     * @param answer
     *            - the puzzle answer
     * @param hint
     *            - a hint to solving the puzzle
     */
    public Puzzle(
        String question,
        String answer,
        String hint)
    {
        this.question = question;
        this.answer = answer;
        this.hint = hint;

        solved = false;
    }

    // ----------------------------------------------------------
    /**
     * Returns the puzzle question
     *
     * @return question
     */
    public String getQuestion()
    {
        return question;
    }

    // ----------------------------------------------------------
    /**
     * Returns the puzzle answer
     *
     * @return answer
     */
    public String getAnswer()
    {
        return answer;
    }

    // ----------------------------------------------------------
    /**
     * Returns the puzzle hint
     *
     * @return hint
     */
    public String getHint()
    {
        return hint;
    }

    // ----------------------------------------------------------
    /**
     * Returns if the puzzle has been solved or not
     *
     * @return solved
     */
    public boolean isSolved()
    {
        return solved;
    }

    // ----------------------------------------------------------
    /**
     * Checks the answer that the player has entered
     * against the puzzle answer
     *
     * @param playerAnswer
     *            - answer that the player has entered
     * @return true if correct, false otherwise
     */
    public boolean checkAnswer(
        String playerAnswer)
    {
        if (playerAnswer != null
            && answer.equalsIgnoreCase(
                playerAnswer.trim()))
        {
            solved = true;

            return true;
        }

        return false;
    }
}