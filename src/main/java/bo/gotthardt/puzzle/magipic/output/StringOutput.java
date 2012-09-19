package bo.gotthardt.puzzle.magipic.output;

import bo.gotthardt.puzzle.magipic.Color;
import bo.gotthardt.puzzle.magipic.MagipicPuzzle;
import bo.gotthardt.puzzle.magipic.Square;
import lombok.RequiredArgsConstructor;

/**
 * Output generator for representing a puzzle's square colors in the same simplified string format as in
 * {@link bo.gotthardt.puzzle.magipic.input.StringInput}.
 *
 * @author Bo Gotthardt
 */
@RequiredArgsConstructor
public class StringOutput {
    private final MagipicPuzzle input;

    /**
     * Get the output. See {@link bo.gotthardt.puzzle.magipic.input.StringInput} for the format.
     *
     * @return the output
     */
    public String getOutput() {
        StringBuilder output = new StringBuilder();
        int numColumns = input.getNumColumns();
        int column = 0;

        for (Square square : input.getSquares()) {
            if (square.hasColor()) {
                if (square.getColor() == Color.BLACK) {
                    output.append("#");
                } else {
                    output.append(".");
                }
            } else {
                output.append(" ");
            }

            column++;
            if (column == numColumns) {
                column = 0;
                output.append("\n");
            }
        }
        return output.toString();
    }
}
