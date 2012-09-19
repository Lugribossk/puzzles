package bo.gotthardt.puzzle.magipic;

import bo.gotthardt.puzzle.magipic.input.PuzzleInput;
import bo.gotthardt.puzzle.magipic.input.StringInput;
import bo.gotthardt.puzzle.magipic.output.StringOutput;
import bo.gotthardt.puzzle.magipic.rule.*;
import bo.gotthardt.util.Interval;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import lombok.Getter;
import org.joor.Reflect;

import java.util.List;
import java.util.Set;

/**
 * Solver for "Magipic"/"Fill-a-Pix" puzzles.
 * <br/>
 * Example:
 * <pre>
 * MagipicPuzzle puzzle = new MagipicPuzzle(ImmutableList.of("111",
 *                                                           "2  ",
 *                                                           "  4",
 *                                                           "1 3"));
 * puzzle.solve();
 * System.out.println(puzzle);
 * </pre>
 *
 * @see StringInput
 * @see <a href="http://en.wikipedia.org/wiki/Pic_Pic">Pic Pic</a>
 * @see <a href="http://www.conceptispuzzles.com/index.aspx?uri=puzzle/fill-a-pix">Conceptis Puzzles</a>
 *
 * @author Bo Gotthardt
 */
public class MagipicPuzzle {
    /**
     * All the rules for solving clues.
     * While the logic is separated into different classes (for readability), the rules only make sense as a complete set.
     */
    private static final List<Class<? extends ClueSolutionRule>> RULES =
            ImmutableList.of(MatchesUncoloredRule.class,
                             ReduceRule.class,
                             StrictSubsetRule.class,
                             IntersectionRule.class,
                             CleanupRule.class);

    private Set<Clue> activeClues;
    /** The squares in this puzzle. */
    @Getter
    private final List<Square> squares;
    /** The number of columns in this puzzle's squares. */
    @Getter
    private final int numColumns;


    /**
     * Convenience constructor that uses {@link StringInput}.
     *
     * @param input the string specification of the puzzle
     * @see StringInput#StringInput(java.util.List)
     */
    public MagipicPuzzle(List<String> input) {
        this(new StringInput(input));
    }

    /**
     * Constructor.
     *
     * @param input the puzzle input
     */
    public MagipicPuzzle(PuzzleInput input) {
        activeClues = input.getClues();
        squares = input.getSquares();
        numColumns = input.getNumColumns();

        Preconditions.checkArgument(!squares.isEmpty(), "Puzzle must have at least one square.");
    }

    /**
     * Solve the puzzle, assuming that it is solvable.
     *
     * @throws IllegalStateException if the puzzle is not solvable
     * @return this modified into solved form
     */
    public MagipicPuzzle solve() {
        while (!activeClues.isEmpty()) {
            Set<Clue> newClues = Sets.newHashSet();

            for (Clue clue : activeClues) {
                boolean solved = false;
                for (Class<? extends ClueSolutionRule> ruleClass : RULES) {
                    // This would be easier with Java 8's ::new constructor references.
                    ClueSolutionRule rule = Reflect.on(ruleClass).create(clue).get();

                    if (rule.hasSolution()) {
                        newClues.addAll(rule.getNewClues());
                        solved = true;
                        break;
                    }
                }

                if (!solved) {
                    newClues.add(clue);
                }
            }

            Preconditions.checkState(!activeClues.equals(newClues), "Puzzle appears unsolvable.");
            activeClues = newClues;
        }

        return this;
    }

    /**
     * Returns whether this puzzle has its squares colored the same as the specified other puzzle.
     * <br/>
     * Always false for puzzle of different sizes.
     *
     * @param other the other puzzle
     * @return whether the squares are colored the same
     */
    public boolean colorEquals(MagipicPuzzle other) {
        if (this.squares.size() != other.squares.size()) {
            return false;
        } else {
            for (int i : Interval.zeroUpTo(this.squares.size())) {
                if (!this.squares.get(i).colorEquals(other.squares.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return new StringOutput(this).getOutput();
    }
}
