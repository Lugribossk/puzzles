package bo.gotthardt.puzzle.magipic.rule;

import bo.gotthardt.puzzle.magipic.Clue;
import bo.gotthardt.puzzle.magipic.Color;
import bo.gotthardt.puzzle.magipic.Square;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

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

        assertThat(rule.hasSolution()).isTrue();
        assertThat(rule.getNewClues()).hasSize(1);

        Clue c2 = ImmutableList.copyOf(rule.getNewClues()).get(0);
        assertThat(c2.getAffectedSquares()).isEqualTo(ImmutableSet.of(s2));
        assertThat(c2.getTargetNumber(Color.WHITE)).isEqualTo(1);
    }
}
