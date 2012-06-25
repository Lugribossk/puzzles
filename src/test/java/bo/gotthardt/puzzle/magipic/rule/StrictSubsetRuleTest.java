package bo.gotthardt.puzzle.magipic.rule;

import bo.gotthardt.puzzle.magipic.Clue;
import bo.gotthardt.puzzle.magipic.Color;
import bo.gotthardt.puzzle.magipic.Square;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link StrictSubsetRule}.
 *
 * @author Bo Gotthardt
 */
public class StrictSubsetRuleTest {
    @Test
    public void testCreateSubsetClue() {
        Square s1 = new Square();
        Square s2 = new Square();
        Square s3 = new Square();
        Clue c1 = new Clue(1, ImmutableSet.of(s1, s2, s3));
        Clue c2 = new Clue(1, ImmutableSet.of(s2, s3));

        StrictSubsetRule rule = new StrictSubsetRule(c1);

        assertTrue(rule.hasSolution());
        assertEquals(1, rule.getNewClues().size());

        Clue c3 = ImmutableList.copyOf(rule.getNewClues()).get(0);

        assertEquals(ImmutableSet.of(s1), c3.getAffectedSquares());
        assertEquals(1, c3.getTargetNumber(Color.WHITE));
    }
}
