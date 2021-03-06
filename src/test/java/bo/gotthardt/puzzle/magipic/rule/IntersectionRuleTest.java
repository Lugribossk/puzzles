package bo.gotthardt.puzzle.magipic.rule;

import bo.gotthardt.puzzle.magipic.Clue;
import bo.gotthardt.puzzle.magipic.Color;
import bo.gotthardt.puzzle.magipic.Square;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Tests for {@link IntersectionRule}.
 *
 * @author Bo Gotthardt
 */
public class IntersectionRuleTest {
    @Test
    public void testIntersectionSplit() {
        Square s1 = new Square();
        Square s2 = new Square();
        Square s3 = new Square();
        Square s4 = new Square();
        Square s5 = new Square();
        Square s6 = new Square();
        Square s7 = new Square();

        Clue c1 = new Clue(3, ImmutableSet.of(s1, s2, s3, s4, s5));
        Clue c2 = new Clue(2, ImmutableSet.of(    s2, s3, s4, s5, s6, s7));

        IntersectionRule rule = new IntersectionRule(c1);

        assertThat(rule.hasSolution()).isTrue();
        assertThat(rule.getNewClues()).hasSize(2);

        Clue c3 = ImmutableList.copyOf(rule.getNewClues()).get(0);

        assertThat(c3.getAffectedSquares()).isEqualTo(ImmutableSet.of(s2, s3, s4, s5));
        assertThat(c3.getTargetNumber(Color.BLACK)).isEqualTo(2);

        Clue c4 = ImmutableList.copyOf(rule.getNewClues()).get(1);

        assertThat(c4.getAffectedSquares()).isEqualTo(ImmutableSet.of(s1));
        assertThat(c4.getTargetNumber(Color.BLACK)).isEqualTo(1);
    }
}
