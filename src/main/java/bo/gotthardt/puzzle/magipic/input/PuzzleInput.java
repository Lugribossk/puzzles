package bo.gotthardt.puzzle.magipic.input;

import bo.gotthardt.puzzle.magipic.Clue;
import bo.gotthardt.puzzle.magipic.Square;

import java.util.List;
import java.util.Set;

/**
 * The input needed to describe a complete puzzle instance.
 *
 * @author Bo Gotthardt
 */
public interface PuzzleInput {
    /**
     * Get the set of clues.
     *
     * @return the clues
     */
    public Set<Clue> getClues();

    /**
     * Get the squares.
     * <br/>
     * Returned in order of first column in first row, second column in first row, etc.
     *
     * @return the squares
     */
    public List<Square> getSquares();

    /**
     * Get the number of columns.
     *
     * @return the number of columns
     */
    int getNumColumns();
}
