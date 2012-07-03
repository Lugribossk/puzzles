package bo.gotthardt.puzzle.magipic.input;

import bo.gotthardt.puzzle.magipic.Color;
import bo.gotthardt.puzzle.magipic.Square;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static bo.gotthardt.puzzle.magipic.fest.FestConditions.color;
import static org.fest.assertions.api.Assertions.assertThat;


/**
 * Tests for {@link StringInput}.
 *
 * @author Bo Gotthardt
 */
public class StringInputTest {
    @Test
    public void testClueInput() {
        StringInput input = new StringInput("  ",
                                            " 4");

        assertThat(input.getSquares()).hasSize(4);
        assertThat(ImmutableList.copyOf(input.getClues()).get(0).getTargetNumber(Color.BLACK)).isEqualTo(4);
    }

    @Test
    public void testSolvedInput() {
        StringInput input = new StringInput("#..",
                                            "#..");

        List<Square> squares = input.getSquares();

        assertThat(squares).hasSize(6);
        assertThat(squares.get(0)).is(color(Color.BLACK));
        assertThat(squares.get(1)).is(color(Color.WHITE));
        assertThat(squares.get(2)).is(color(Color.WHITE));
        assertThat(squares.get(3)).is(color(Color.BLACK));
        assertThat(squares.get(4)).is(color(Color.WHITE));
        assertThat(squares.get(5)).is(color(Color.WHITE));
    }

    @Test
    public void testFileInput() throws IOException {
        StringInput input = new StringInput(new File(this.getClass().getResource("/StringInputTest1.txt").getFile()));

        assertThat(input.getSquares()).hasSize(9);
        assertThat(ImmutableList.copyOf(input.getClues()).get(0).getTargetNumber(Color.BLACK)).isEqualTo(9);
    }
}
