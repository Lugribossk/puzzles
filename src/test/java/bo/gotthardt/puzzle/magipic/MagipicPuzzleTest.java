package bo.gotthardt.puzzle.magipic;

import org.junit.Test;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for {@link MagipicPuzzle}.
 *
 * @author Bo Gotthardt
 */
public class MagipicPuzzleTest {
    @Test
    public void testMiddle9() {
        assertSolution(of("###",
                          "###",
                          "###"),

                       of("   ",
                          " 9 ",
                          "   "));
    }

    @Test
    public void testMiddle0() {
        assertSolution(of("...",
                          "...",
                          "..."),

                       of("   ",
                          " 0 ",
                          "   "));
    }

    @Test
    public void testCorner4() {
        assertSolution(of("##",
                          "##"),

                       of("  ",
                          " 4"));
    }

    @Test
    public void testTwoClues() {
        assertSolution(of("...#",
                          "...#",
                          "...#"),

                       of("    ",
                          " 0 3",
                          "    "));
    }

    @Test
    public void testFourClues() {
        assertSolution(of("..#..",
                          "..#..",
                          "..#.."),

                       of("     ",
                          "03 30",
                          "     "));
    }

    @Test
    public void testSubsetRequired() {
        assertSolution(of("...",
                          "...",
                          ".#."),

                       of(" 0 ",
                          "111",
                          "   "));
    }

    @Test
    public void testMultipleSubsets() {
        assertSolution(of("...",
                          ".#.",
                          ".##",
                          "..#"),

                       of("111",
                          "2  ",
                          "  4",
                          "1 3"));
    }

    @Test
    public void test5x5BasicExample() {
        assertSolution(of("###..",
                          "####.",
                          "#####",
                          "##.#.",
                          "####."),

                       of("    1",
                          " 9   ",
                          " 88  ",
                          "    4",
                          "4 5 2"));
    }

    @Test
    public void test10x10BasicExample() {
        assertSolution(of(".##.....##",
                          "...#...###",
                          "..###..###",
                          ".##.##.#..",
                          ".#...####.",
                          "##..##..##",
                          "#...#....#",
                          "#...#....#",
                          "##..##..##",
                          ".########."),

                       of(" 23  0    ",
                          "    3 2  6",
                          "  5 53 574",
                          " 4 5 5 6 3",
                          "  4 5 6  3",
                          "   2 5    ",
                          "4 1   11  ",
                          "4 1   1 4 ",
                          "    6    4",
                          " 44    4  "));
    }

    @Test
    public void test5x5AdvancedExample() {
        assertSolution(of("..##.",
                          "..##.",
                          "##.##",
                          ".###.",
                          "##.##"),

                       of("0 44 ",
                          " 4 6 ",
                          "3 76 ",
                          " 6 65",
                          "    3"));
    }

    @Test
    public void test10x10AdvancedExample() {
        assertSolution(of("..##......",
                          ".#..#.....",
                          "##..#.....",
                          "..#.#..#.#",
                          ".#.####.##",
                          ".#.#...#.#",
                          ".#..###..#",
                          "..#.....##",
                          "..#######.",
                          "##########"),

                       of("  33      ",
                          "3     0 0 ",
                          "  34 3    ",
                          "3 4    3  ",
                          "23 5 44  4",
                          "  5466 4 4",
                          "     33  4",
                          " 3  565  4",
                          "   7   7 5",
                          " 4  6 6 5 "));
    }

    @Test(expected = Exception.class)
    public void testUnsolvableNoInfiniteLoop() {
        MagipicPuzzle s = new MagipicPuzzle(of(" 1"));

        s.solve();
    }

    @Test
    public void testColorEquals() {
        MagipicPuzzle p1 = new MagipicPuzzle(of("###",
                                                "###",
                                                "###"));
        MagipicPuzzle p2 = new MagipicPuzzle(of("###",
                                                "###",
                                                "###"));
        MagipicPuzzle p3 = new MagipicPuzzle(of("###",
                                                "#.#",
                                                "###"));

        assertTrue(p1.colorEquals(p2));
        assertFalse(p1.colorEquals(p3));
    }


    /**
     * Assert that a puzzle string solves to be color equals to another expected puzzle string.
     *
     * @param expected the expected solution
     * @param actualToSolve the puzzle to solve.
     */
    private static void assertSolution(List<String> expected, List<String> actualToSolve) {
        MagipicPuzzle s1 = new MagipicPuzzle(expected);
        MagipicPuzzle s2 = new MagipicPuzzle(actualToSolve);

        s2.solve();

        assertTrue(s1.colorEquals(s2));
    }
}
