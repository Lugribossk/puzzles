package bo.gotthardt.puzzle.magipic;

import bo.gotthardt.puzzle.magipic.input.XMLClueInput;
import bo.gotthardt.puzzle.magipic.input.XMLSolvedInput;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Tests using complex puzzles downloaded from <a href="http://www.conceptispuzzles.com/index.aspx?uri=puzzle/fill-a-pix">Conceptis</a>.
 *
 * @author Bo Gotthardt
 */
public class ConceptisPuzzlesTest {
    @Test
    public void testAdvanced() {
        assertSolvesCorrectly("/conceptis1.xml");
    }

    @Test
    public void testIntersectionRequiredAdvanced() {
        // Requires IntersectionRule to solve.
        assertSolvesCorrectly("/conceptis2.xml");
    }

    /**
     * Assert that a specified resource file containing an XML puzzle and solution is solved correctly.
     *
     * @param xmlFile the file, remember the starting '/'
     */
    private void assertSolvesCorrectly(String xmlFile) {
        File file = new File(this.getClass().getResource(xmlFile).getFile());
        MagipicPuzzle clues = new MagipicPuzzle(new XMLClueInput(file));
        MagipicPuzzle solution = new MagipicPuzzle(new XMLSolvedInput(file));

        clues.solve();

        assertTrue(clues.colorEquals(solution));
    }
}
