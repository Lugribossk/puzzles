package bo.gotthardt.puzzle.magipic.input;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link XMLClueInput}.
 *
 * @author Bo Gotthardt
 */
public class XMLClueInputTest {
    @Test
    public void testReadFile() {
        PuzzleInput input = new XMLClueInput(new File(this.getClass().getResource("/conceptis1.xml").getFile()));

        assertEquals(20 * 20, input.getSquares().size());
        // 248 occurrences of '-1'.
        assertEquals(20 * 20 - 248, input.getClues().size());
    }
}
