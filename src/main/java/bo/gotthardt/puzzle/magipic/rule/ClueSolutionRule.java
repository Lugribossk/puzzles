package bo.gotthardt.puzzle.magipic.rule;

import bo.gotthardt.puzzle.magipic.Clue;
import bo.gotthardt.puzzle.magipic.Square;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Rule for possibly solving a clue.
 * <br/>
 * A clue is solved by coloring its squares or decomposing it into one or more smaller clues.
 *
 * @author Bo Gotthardt
 */
public abstract class ClueSolutionRule {
    protected final Clue clue;
    protected final Set<Square> affectedSquares;
    protected final Set<Square> uncoloredSquares;
    private Optional<Set<Clue>> solution = Optional.absent();


    /**
     * Constructor.
     * <br/>
     * Subclasses <b>must</b> have an identical constructor.
     *
     * @param clue the clue to attempt to solve.
     */
    public ClueSolutionRule(Clue clue) {
        this.clue = clue;
        affectedSquares = clue.getAffectedSquares();
        uncoloredSquares = Sets.filter(affectedSquares, Square.UNCOLORED);

        trySolve();
    }

    /**
     * Returns whether the rule has been able to solve this clue.
     *
     * @return whether the rule has been able to solve this clue
     */
    public boolean hasSolution() {
        return solution.isPresent();
    }

    /**
     * Returns the new clues created by solving the clue.
     * <br/>
     * Caller <b>must</b> check {@link #hasSolution()} first.
     *
     * @return the new clues, might be empty
     */
    public Set<Clue> getNewClues() {
        return solution.get();
    }

    /**
     * Set the solution if one has been found.
     *
     * @param newClues the new clues, possibly none
     */
    protected void setSolution(Clue... newClues) {
        Preconditions.checkState(!solution.isPresent(), "Cannot set solution more than once.");
        clue.markSolved();
        solution = Optional.<Set<Clue>>of(ImmutableSet.copyOf(newClues));
    }

    /**
     * Try to solve the provided clue.
     */
    protected abstract void trySolve();
}
