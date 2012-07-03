package bo.gotthardt.puzzle.magipic.input;

import bo.gotthardt.puzzle.magipic.Color;
import com.google.common.collect.Collections2;
import org.junit.Test;

import java.io.File;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Tests for {@link XMLSolvedInput}.
 *
 * @author Bo Gotthardt
 */
public class XMLSolvedInputTest {
    @Test
    public void testReadFile() {
        PuzzleInput input = new XMLSolvedInput(new File(this.getClass().getResource("/conceptis1.xml").getFile()));

        assertThat(Collections2.filter(input.getSquares(), Color.BLACK)).hasSize(223);
        assertThat(Collections2.filter(input.getSquares(), Color.WHITE)).hasSize(20 * 20 - 223);
    }
}
