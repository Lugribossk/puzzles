package bo.gotthardt.puzzle.magipic;

import org.junit.Test;

import java.util.List;

import static bo.gotthardt.puzzle.magipic.fest.FestConditions.colorEqualsTo;
import static com.google.common.collect.ImmutableList.of;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Tests for {@link MagipicPuzzle}.
 *
 * @author Bo Gotthardt
 */
public class MagipicPuzzleTest {
    @Test
    public void testMiddle9() {
        assertSolvesTo(of("   ",
                          " 9 ",
                          "   "),
                       of("###",
                          "###",
                          "###"));
    }

    @Test
    public void testMiddle0() {
        assertSolvesTo(of("   ",
                          " 0 ",
                          "   "),
                       of("...",
                          "...",
                          "..."));
    }

    @Test
    public void testCorner4() {
        assertSolvesTo(of("  ",
                          " 4"),
                       of("##",
                          "##"));
    }

    @Test
    public void testTwoClues() {
        assertSolvesTo(of("    ",
                          " 0 3",
                          "    "),
                       of("...#",
                          "...#",
                          "...#"));
    }

    @Test
    public void testFourClues() {
        assertSolvesTo(of("     ",
                          "03 30",
                          "     "),
                       of("..#..",
                          "..#..",
                          "..#.."));
    }

    @Test
    public void testSubsetRequired() {
        assertSolvesTo(of(" 0 ",
                          "111",
                          "   "),
                       of("...",
                          "...",
                          ".#."));
    }

    @Test
    public void testMultipleSubsets() {
        assertSolvesTo(of("111",
                          "2  ",
                          "  4",
                          "1 3"),
                       of("...",
                          ".#.",
                          ".##",
                          "..#"));
    }

    @Test
    public void test5x5BasicExample() {
        assertSolvesTo(of("    1",
                          " 9   ",
                          " 88  ",
                          "    4",
                          "4 5 2"),
                       of("###..",
                          "####.",
                          "#####",
                          "##.#.",
                          "####."));
    }

    @Test
    public void test10x10BasicExample() {
        assertSolvesTo(of(" 23  0    ",
                          "    3 2  6",
                          "  5 53 574",
                          " 4 5 5 6 3",
                          "  4 5 6  3",
                          "   2 5    ",
                          "4 1   11  ",
                          "4 1   1 4 ",
                          "    6    4",
                          " 44    4  "),
                       of(".##.....##",
                          "...#...###",
                          "..###..###",
                          ".##.##.#..",
                          ".#...####.",
                          "##..##..##",
                          "#...#....#",
                          "#...#....#",
                          "##..##..##",
                          ".########."));
    }

    @Test
    public void test5x5AdvancedExample() {
        assertSolvesTo(of("0 44 ",
                          " 4 6 ",
                          "3 76 ",
                          " 6 65",
                          "    3"),
                       of("..##.",
                          "..##.",
                          "##.##",
                          ".###.",
                          "##.##"));
    }

    @Test
    public void test10x10AdvancedExample() {
        assertSolvesTo(of("  33      ",
                          "3     0 0 ",
                          "  34 3    ",
                          "3 4    3  ",
                          "23 5 44  4",
                          "  5466 4 4",
                          "     33  4",
                          " 3  565  4",
                          "   7   7 5",
                          " 4  6 6 5 "),
                       of("..##......",
                          ".#..#.....",
                          "##..#.....",
                          "..#.#..#.#",
                          ".#.####.##",
                          ".#.#...#.#",
                          ".#..###..#",
                          "..#.....##",
                          "..#######.",
                          "##########"));
    }

    @Test(expected = Exception.class, timeout = 1000)
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

        assertThat(p1).is(colorEqualsTo(p2));
        assertThat(p1).isNot(colorEqualsTo(p3));
    }



    /**
     * Assert that the specified puzzle solves to be identical to the other specified puzzle.
     *
     * @param actualToSolve the actual puzzle to solve
     * @param expected the expected solution
     */
    private static void assertSolvesTo(List<String> actualToSolve, List<String> expected) {
        MagipicPuzzle s1 = new MagipicPuzzle(expected);
        MagipicPuzzle s2 = new MagipicPuzzle(actualToSolve);

        s2.solve();

        assertThat(s2).is(colorEqualsTo(s1));
    }
}