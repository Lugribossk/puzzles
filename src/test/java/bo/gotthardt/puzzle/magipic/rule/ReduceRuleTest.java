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
 * Tests for {@link ReduceRule}.
 *
 * @author Bo Gotthardt
 */
public class ReduceRuleTest {
    @Test
    public void testColorForRemaining() {
        Square s1 = new Square().setColor(Color.BLACK);
        Square s2 = new Square();
        Clue c1 = new Clue(1, ImmutableSet.of(s1, s2));

        ReduceRule rule = new ReduceRule(c1);

        assertTrue(rule.hasSolution());
        assertEquals(1, rule.getNewClues().size());

        Clue c2 = ImmutableList.copyOf(rule.getNewClues()).get(0);
        assertEquals(ImmutableSet.of(s2), c2.getAffectedSquares());
        assertEquals(1, c2.getTargetNumber(Color.WHITE));
    }
}
