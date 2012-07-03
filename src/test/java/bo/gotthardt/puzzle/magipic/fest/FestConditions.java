package bo.gotthardt.puzzle.magipic.fest;

import bo.gotthardt.puzzle.magipic.Color;
import bo.gotthardt.puzzle.magipic.MagipicPuzzle;
import bo.gotthardt.puzzle.magipic.Square;
import org.fest.assertions.core.Condition;

/**
 * Utility methods for creating FEST conditions.
 *
 * @author Bo Gotthardt
 */
public class FestConditions {

    /**
     * Creates a condition for a puzzle to be colorEquals() to the specified expected puzzle.
     *
     * @param expected the expected puzzle
     * @return the condition
     */
    public static Condition<MagipicPuzzle> colorEqualsTo(final MagipicPuzzle expected) {
        return new Condition<MagipicPuzzle>() {
            @Override
            public boolean matches(MagipicPuzzle actual) {
                return actual.colorEquals(expected);
            }

            @Override
            public String toString() {
                return expected.toString();
            }
        };
    }

    /**
     * Creates a condition for a square to fulfill the specified color predicate.
     *
     * @param color the color
     * @return the condition
     */
    public static Condition<Square> color(final Color color) {
        return new Condition<Square>() {
            @Override
            public boolean matches(Square value) {
                return color.apply(value);
            }

            @Override
            public String toString() {
                return color.toString();
            }
        };
    }
}
