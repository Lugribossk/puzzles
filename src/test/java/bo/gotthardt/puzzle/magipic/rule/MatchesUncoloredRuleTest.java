package bo.gotthardt.puzzle.magipic.rule;

import bo.gotthardt.puzzle.magipic.Clue;
import bo.gotthardt.puzzle.magipic.Color;
import bo.gotthardt.puzzle.magipic.Square;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import static bo.gotthardt.puzzle.magipic.fest.FestConditions.color;
import static org.fest.assertions.api.Assertions.assertThat;

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

        assertThat(rule.hasSolution()).isTrue();
        assertThat(rule.getNewClues()).isEmpty();
        assertThat(square).is(color(Color.BLACK));
    }

    @Test
    public void testColorWhite() {
        Square square = new Square();
        Clue clue = new Clue(0, ImmutableSet.of(square));

        MatchesUncoloredRule rule = new MatchesUncoloredRule(clue);

        assertThat(rule.hasSolution()).isTrue();
        assertThat(rule.getNewClues()).isEmpty();
        assertThat(square).is(color(Color.WHITE));
    }
}