package bo.gotthardt.puzzle.magipic;

import com.google.common.base.Predicate;

/**
 * A Magipic square color.
 * <br/>
 * Also a predicate for a square having a color, and being that color.
 *
 * @author Bo Gotthardt
 */
public enum Color implements Predicate<Square> {
    /**
     * Black.
     */
    BLACK,
    /**
     * White.
     */
    WHITE;

    @Override
    public boolean apply(Square square) {
        return square.hasColor() && square.getColor() == this;
    }
}
