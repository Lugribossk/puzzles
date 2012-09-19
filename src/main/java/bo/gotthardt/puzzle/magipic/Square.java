package bo.gotthardt.puzzle.magipic;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Set;

/**
 * A Magipic square, possibly colored and affected by a set of clues.
 * <br/>
 * Does not include the clue information directly to allow new smaller clues to be generated easily during solving.
 *
 * @author Bo Gotthardt
 */
public class Square {
    private Optional<Color> color = Optional.absent();
    /** The set of clues that affect this square. */
    @Getter
    private final Set<Clue> clues = Sets.newHashSet();


    /**
     * Add the specified clue to the set of clues that affect this square.
     * <br/>
     * Must not have been added already.
     *
     * @param clue the clue
     * @return this, for chaining
     */
    public Square addClue(Clue clue) {
        Preconditions.checkArgument(!clues.contains(clue), "Cannot add a clue that is already added.");
        clues.add(clue);

        return this;
    }

    /**
     * Remove the specified clue from the set of clues that affect this square.
     * <br/>
     * Must have been added already. Must not be the last clue affecting an uncolored square.
     *
     * @param clue the clue
     * @return this, for chaining
     */
    public Square removeClue(Clue clue) {
        Preconditions.checkArgument(clues.contains(clue), "Cannot remove a clue that is not added.");
        Preconditions.checkState(hasColor() || clues.size() > 1, "Cannot remove the last clue for an uncolored square.");
        clues.remove(clue);

        return this;
    }

    /**
     * Get the color of this square, assuming it has been set.
     * <br/>
     * Caller <b>must</b> check {@link Square#hasColor()} first.
     *
     * @return the color
     */
    public Color getColor() {
        return color.get();
    }

    /**
     * Returns whether this square has been given a color.
     *
     * @return whether this square has been given a color
     * @see Square#UNCOLORED
     */
    public boolean hasColor() {
        return color.isPresent();
    }

    /**
     * Set the color of this square.
     * <br/>
     * Must not already have been colored.
     *
     * @param color the color
     * @return this, for chaining
     */
    public Square setColor(Color color) {
        Preconditions.checkState(!hasColor(), "Cannot set color for an already colored square.");
        this.color = Optional.of(color);

        return this;
    }

    /**
     * Returns whether this square has the same color (or absence of such) as the specified other square.
     *
     * @param other the other square
     * @return whether this square has the same color (or absence of such)
     */
    public boolean colorEquals(Square other) {
        return color.equals(other.color);
    }

    /**
     * Predicate for checking that a square has not been given a color.
     *
     * @see Square#hasColor()
     */
    public static final Predicate<Square> UNCOLORED = new Predicate<Square>() {
        @Override
        public boolean apply(Square square) {
            return !square.hasColor();
        }
    };
}
