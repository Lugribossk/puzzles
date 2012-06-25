package bo.gotthardt.puzzle.magipic.rule;

import bo.gotthardt.puzzle.magipic.Clue;
import bo.gotthardt.puzzle.magipic.Color;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

/**
 * Rule for cleaning up clues that may have been solved implicitly by other clues.
 * <br/>
 * Placing this logic by itself simplifies the other rules.
 *
 * @author Bo Gotthardt
 */
public class CleanupRule extends ClueSolutionRule {
    /**
     * Constructor.
     *
     * @param clue the clue to try to solve
     */
    public CleanupRule(Clue clue) {
        super(clue);
    }

    @Override
    protected void trySolve() {
        if (uncoloredSquares.isEmpty()) {
            Preconditions.checkState(clue.getTargetNumber(Color.BLACK) == Sets.filter(affectedSquares, Color.BLACK).size(),
                                     "Found implicitly solved clue with wrong solution.");
            setSolution();
        }
    }
}
