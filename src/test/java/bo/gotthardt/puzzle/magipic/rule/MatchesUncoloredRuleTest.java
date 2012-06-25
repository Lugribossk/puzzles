package bo.gotthardt.puzzle.magipic.rule;

import bo.gotthardt.puzzle.magipic.Clue;
import bo.gotthardt.puzzle.magipic.Color;
import bo.gotthardt.puzzle.magipic.Square;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link MatchesUncoloredRule}.
 *
 * @author Bo Gotthardt
 */
public class MatchesUncoloredRuleTest {
    @Test
    public void testColorBlack() {
        Square square = new Square();
        Clue clue = new Clue(1, ImmutableSet.of(square));

        MatchesUncoloredRule rule = new MatchesUncoloredRule(clue);

        assertTrue(rule.hasSolution());
        assertEquals(0, rule.getNewClues().size());
        assertEquals(Color.BLACK, square.getColor());
    }

    @Test
    public void testColorWhite() {
        Square square = new Square();
        Clue clue = new Clue(0, ImmutableSet.of(square));

        MatchesUncoloredRule rule = new MatchesUncoloredRule(clue);

        assertTrue(rule.hasSolution());
        assertEquals(0, rule.getNewClues().size());
        assertEquals(Color.WHITE, square.getColor());
    }
}
