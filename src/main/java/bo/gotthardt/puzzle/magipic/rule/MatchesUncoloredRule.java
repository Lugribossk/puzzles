package bo.gotthardt.puzzle.magipic.rule;

import bo.gotthardt.puzzle.magipic.Clue;
import bo.gotthardt.puzzle.magipic.Color;
import bo.gotthardt.puzzle.magipic.Square;

/**
 * Rule for coloring squares. If the clue matches the number of affected uncolored squares, they can be colored and the clue is solved.
 *
 * @author Bo Gotthardt
 */
public class MatchesUncoloredRule extends ClueSolutionRule {
    /**
     * Constructor.
     *
     * @param clue the clue to try to solve
     */
    public MatchesUncoloredRule(Clue clue) {
        super(clue);
    }

    @Override
    protected void trySolve() {
        if (affectedSquares.size() == uncoloredSquares.size()) {
            // All affected are uncolored.
            for (Color color : Color.values()) {
                if (uncoloredSquares.size() == clue.getTargetNumber(color)) {
                    // The number of uncolored squares matches the clue, so color them.
                    for (Square square : uncoloredSquares) {
                        square.setColor(color);
                    }

                    // The clue is solved and no new one has been generated.
                    setSolution();
                    return;
                }
            }
        }
    }
}
