package bo.gotthardt.puzzle.magipic.rule;

import bo.gotthardt.puzzle.magipic.Clue;
import bo.gotthardt.puzzle.magipic.Color;
import bo.gotthardt.puzzle.magipic.Square;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Rule for decomposing a clue, based on it having another clue as a strict subset. If another clue only affects a
 * strict subset of this clue, then the affected squares outside this subset must hold the excess part of the clue.
 *
 * Example:
 * <pre>
 * Clue:      X(2)
 *          / | \
 * Squares: a b c
 *          \ /
 * Clue:     Y(1)
 * </pre>
 * Y affects a strict subset of X, meaning that of X's 2 black squares among a, b, c, 1 of them must be c:
 * <pre>
 * New clue:    Z(1)
 *              |
 * Squares: a b c
 *          \ /
 * Clue:     Y(1)</pre>
 *
 * @author Bo Gotthardt
 */
public class StrictSubsetRule extends ClueSolutionRule {
    /**
     * Constructor.
     *
     * @param clue the clue to try to solve
     */
    public StrictSubsetRule(Clue clue) {
        super(clue);
    }

    @Override
    protected void trySolve() {
        if (affectedSquares.size() == uncoloredSquares.size()) {
            // All affected are uncolored.

            // Get all the other clues that have a square in common with this clue.
            Set<Clue> otherClues = Sets.newHashSet();
            for (Square square : affectedSquares) {
                otherClues.addAll(square.getClues());
            }

            for (Clue otherClue : otherClues) {
                Set<Square> otherAffected = otherClue.getAffectedSquares();
                if (affectedSquares.containsAll(otherAffected)) {
                    // The other clue solely affects a strict subset of this clue, so we can create a new clue for the squares outside that subset.
                    Set<Square> outsideSubset = Sets.difference(affectedSquares, otherAffected);
                    if (outsideSubset.size() > 0) {
                        int outsideSubsetTargetBlack = clue.getTargetNumber(Color.BLACK) - otherClue.getTargetNumber(Color.BLACK);

                        // All the information in the current clue is contained in the other clue, and the newly created one.
                        setSolution(new Clue(outsideSubsetTargetBlack, outsideSubset));
                        return;
                    }
                }
            }
        }
    }
}
