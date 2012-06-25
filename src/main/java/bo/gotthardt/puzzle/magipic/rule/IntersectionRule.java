package bo.gotthardt.puzzle.magipic.rule;

import bo.gotthardt.puzzle.magipic.Clue;
import bo.gotthardt.puzzle.magipic.Color;
import bo.gotthardt.puzzle.magipic.Square;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Rule for decomposing a clue, based on it intersecting another clue. The other clue can "force" all the current clue's
 * affected squares outside the intersection to be colored, which can be expressed with two new clues.
 *
 * Example:
 * <pre>
 * Clue:        W(3)
 *          / / | \ \
 * Squares: a b c d e f g
 *            \ \ \ / / /
 * Clue:           X(2)
 * </pre>
 * W intersects with X in such a way that 2 of its colored squares must be in the intersection:
 * <pre>
 * New:   Y(1)  Z(2)
 *          \ / / \ \
 * Squares: a b c d e f g
 *            \ \ \ / / /
 * Clue:           X(2)
 * </pre>
 *
 * @author Bo Gotthardt
 */
public class IntersectionRule extends ClueSolutionRule {
    /**
     * Constructor.
     *
     * @param clue the clue to try to solve
     */
    public IntersectionRule(Clue clue) {
        super(clue);
    }

    @Override
    protected void trySolve() {
        // TODO Can this and StrictSubsetRule be generalized together? It feels like they are special cases of a more general rule.
        if (affectedSquares.size() == uncoloredSquares.size()) {
            // All affected are uncolored.

            // Get all the other clues that have a square in common with this clue.
            Set<Clue> otherClues = Sets.newHashSet();
            for (Square square : affectedSquares) {
                otherClues.addAll(square.getClues());
            }

            for (Clue otherClue : otherClues) {
                Set<Square> otherAffected = otherClue.getAffectedSquares();
                if (!affectedSquares.containsAll(otherAffected)) {
                    // The clues affect more than a subset of each other.
                    Set<Square> outsideSubset = Sets.difference(affectedSquares, otherAffected);

                    // TODO Would checking this for both black and white help?
                    int numBlackInIntersection = clue.getTargetNumber(Color.BLACK) - outsideSubset.size();
                    if (!outsideSubset.isEmpty() && otherClue.getTargetNumber(Color.BLACK) == numBlackInIntersection) {
                        // The number of black squares in the intersection du to the other clue means that all the squares
                        // we have outside the intersection must be black.
                        Set<Square> intersection = Sets.intersection(affectedSquares, otherAffected);

                        setSolution(new Clue(numBlackInIntersection, intersection), // Z
                                    new Clue(clue.getTargetNumber(Color.BLACK) - numBlackInIntersection, outsideSubset)); // Y
                        return;
                    }
                }
            }
        }
    }
}
