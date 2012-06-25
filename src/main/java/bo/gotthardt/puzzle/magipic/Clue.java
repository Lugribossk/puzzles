package bo.gotthardt.puzzle.magipic;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;

import javax.annotation.concurrent.Immutable;
import java.util.Set;

/**
 * A Magicpic clue as to how a set of squares should be colored.
 * <br/>
 * Generalized to not only affect 4/6/9 adjacent squares so intermediate clues can be generated during puzzle solving.
 *
 * @author Bo Gotthardt
 */
@Immutable
public class Clue {
    private final int targetBlack;
    private final Set<Square> affectedSquares;

    /**
     * Constructor.
     *
     * @param targetBlack the number of affected squares that should be {@link Color#BLACK}, must not be larger than the number of affected squares
     * @param affectedSquares the squares affected by this clue, must not be empty
     */
    public Clue(int targetBlack, Set<Square> affectedSquares) {
        Preconditions.checkArgument(!affectedSquares.isEmpty(), "Clue must affect at least one square.");
        Preconditions.checkArgument(targetBlack <= affectedSquares.size(), "Clue cannot require more black squares than it affects.");
        this.targetBlack = targetBlack;
        this.affectedSquares = ImmutableSet.copyOf(affectedSquares);

        for (Square s : affectedSquares) {
            s.addClue(this);
        }
    }

    /**
     * Get the squares affected by this clue.
     *
     * @return the set of squares, not empty
     */
    public Set<Square> getAffectedSquares() {
        return affectedSquares;
    }

    /**
     * Get the number of affected squares that should be the specified color.
     *
     * @param color the color
     * @return the number of affected squares that should be the specified color
     */
    public int getTargetNumber(Color color) {
        if (color == Color.BLACK) {
            return targetBlack;
        } else {
            return affectedSquares.size() - targetBlack;
        }
    }

    /**
     * Mark this clue as solved, removing it from affecting any squares.
     */
    public void markSolved() {
        for (Square s : affectedSquares) {
            s.removeClue(this);
        }
    }
}
