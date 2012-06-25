package bo.gotthardt.puzzle.magipic.rule;

import bo.gotthardt.puzzle.magipic.Clue;
import bo.gotthardt.puzzle.magipic.Color;
import bo.gotthardt.puzzle.magipic.Square;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Rule for reducing the number of squares a clue affects. If it affects one or more colored squares, a new adjusted
 * clue that only affects the uncolored squares is generated. This means that the other rules only need to work with
 * clues that only affect uncolored squares, which simplifies them.
 *
 * @author Bo Gotthardt
 */
public class ReduceRule extends ClueSolutionRule {
    /**
     * Constructor.
     *
     * @param clue the clue to try to solve
     */
    public ReduceRule(Clue clue) {
        super(clue);
    }

    @Override
    protected void trySolve() {
        // TODO Can this be combined with CleanupRule?
        if (affectedSquares.size() > uncoloredSquares.size() && !uncoloredSquares.isEmpty()) {
            // Some (but not all) of the affected are colored, so replace with a "reduced" clue for the uncolored.
            Set<Square> blackSquares = Sets.filter(affectedSquares, Color.BLACK);
            int uncoloredTargetBlack = clue.getTargetNumber(Color.BLACK) - blackSquares.size();

            // All the information in the current clue is contained in the new clue.
            setSolution(new Clue(uncoloredTargetBlack, uncoloredSquares));
        }
    }
}
