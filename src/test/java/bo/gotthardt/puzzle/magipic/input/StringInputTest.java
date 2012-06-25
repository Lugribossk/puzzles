package bo.gotthardt.puzzle.magipic.input;

import bo.gotthardt.puzzle.magipic.Color;
import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;


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

        assertEquals(4, input.getSquares().size());
        assertEquals(4, ImmutableList.copyOf(input.getClues()).get(0).getTargetNumber(Color.BLACK));
    }

    @Test
    public void testSolvedInput() {
        StringInput input = new StringInput("#..",
                                            "#..");

        assertEquals(6, input.getSquares().size());
        assertEquals(Color.BLACK, input.getSquares().get(0).getColor());
        assertEquals(Color.WHITE, input.getSquares().get(1).getColor());
        assertEquals(Color.WHITE, input.getSquares().get(2).getColor());
        assertEquals(Color.BLACK, input.getSquares().get(3).getColor());
        assertEquals(Color.WHITE, input.getSquares().get(4).getColor());
        assertEquals(Color.WHITE, input.getSquares().get(5).getColor());
    }

    @Test
    public void testFileInput() throws IOException {
        StringInput input = new StringInput(new File(this.getClass().getResource("/StringInputTest1.txt").getFile()));

        assertEquals(9, input.getSquares().size());
        assertEquals(9, ImmutableList.copyOf(input.getClues()).get(0).getTargetNumber(Color.BLACK));
    }
}
