package bo.gotthardt.puzzle.magipic.input;

import bo.gotthardt.puzzle.magipic.Clue;
import bo.gotthardt.puzzle.magipic.Square;
import bo.gotthardt.util.Interval;
import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.List;
import java.util.Set;

/**
 * Abstract functionality for creating puzzle input based on reading and creating a grid of squares.
 *
 * @author Bo Gotthardt
 */
public abstract class GridInput implements PuzzleInput {
    private ArrayTable<Integer, Integer, Square> squares;
    @Getter
    protected final Set<Clue> clues = Sets.newHashSet();
    protected int numRows;
    @Getter
    protected int numColumns;

    @Override
    public List<Square> getSquares() {
        return ImmutableList.copyOf(squares.values());
    }

    /**
     * Get the squares that are adjacent to the specified row/column coordinates.
     * Includes diagonally adjacent squares adn the square itself.
     *
     * @param row the row
     * @param column the column
     * @return the adjacent squares
     */
    protected Set<Square> adjacent(int row, int column) {
        Set<Square> output = Sets.newHashSet();

        for (int rowTarget : Interval.range(row - 1, row + 1)) {
            for (int columnTarget : Interval.range(column - 1, column + 1)) {
                if (validCoordinates(rowTarget, columnTarget)) {
                    output.add(squares.at(rowTarget, columnTarget));
                }
            }
        }

        return output;
    }

    /**
     * Returns whether the specified row/column coordinates are valid.
     *
     * @param row the row
     * @param column the column
     * @return whether the coordinates are valid
     */
    protected boolean validCoordinates(int row, int column) {
        return Interval.zeroUpTo(numRows).contains(row) &&
               Interval.zeroUpTo(numColumns).contains(column);
    }

    /**
     * Get the square at the specified row/column coordinates.
     *
     * @param row the row
     * @param column the column
     * @return the square at the coordinates
     */
    protected Square at(int row, int column) {
        Preconditions.checkArgument(validCoordinates(row, column), "Invalid table coordinates %s, %s", row, column);
        return squares.at(row, column);
    }

    /**
     * Create an ArrayTable with the specified number of rows and column, and initialized with newly instantiated squares.
     */
    protected void initializeSquares() {
        squares = ArrayTable.create(Interval.zeroUpTo(numRows), Interval.zeroUpTo(numColumns));

        for (int row : Interval.zeroUpTo(numRows)) {
            for (int column : Interval.zeroUpTo(numColumns)) {
                squares.set(row, column, new Square());
            }
        }
    }
}
