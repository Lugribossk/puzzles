package bo.gotthardt.puzzle.magipic.input;

import org.junit.Test;

import java.io.File;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Tests for {@link XMLClueInput}.
 *
 * @author Bo Gotthardt
 */
public class XMLClueInputTest {
    @Test
    public void testReadFile() {
        PuzzleInput input = new XMLClueInput(new File(this.getClass().getResource("/conceptis1.xml").getFile()));

        assertThat(input.getSquares()).hasSize(20 * 20);
        // 248 occurrences of '-1'.
        assertThat(input.getClues()).hasSize(20 * 20 - 248);
    }
}
