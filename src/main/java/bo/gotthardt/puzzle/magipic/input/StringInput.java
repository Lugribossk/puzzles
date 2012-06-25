package bo.gotthardt.puzzle.magipic.input;

import bo.gotthardt.puzzle.magipic.Clue;
import bo.gotthardt.puzzle.magipic.Color;
import bo.gotthardt.puzzle.magipic.Square;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Input reader for a simplified character-based puzzle format:
 *
 * <ul>
 *     <li>'0-9': The number of adjacent black squares. Uncolored.</li>
 *     <li>' ': Uncolored.</li>
 *     <li>'#': Colored {@link Color#BLACK}.</li>
 *     <li>'.': Colored {@link Color#WHITE}.</li>
 * </ul>
 * Each row is a separate line, and all rows must be equally long, and only contain the mentioned characters.
 * <br/>
 * Examples:
 * <pre>
 * 111
 * 2
 *   4
 * 1 3</pre>
 *<pre>
 * ...
 * .#.
 * .##
 * ..#</pre>
 * (Note how this is insufficient to specify partially solved puzzles where a clue square has a color.)
 *
 * @author Bo Gotthardt
 */
public class StringInput extends GridInput {

    /**
     * Constructor. Reads input as UTF-8 from the specified file.
     *
     * @param file the file
     * @throws IOException on IO errors
     */
    public StringInput(File file) throws IOException {
        this(Files.readLines(file, Charsets.UTF_8));
    }

    /**
     * Convenience constructor.
     *
     * @param input the input
     */
    public StringInput(String... input) {
        this(ImmutableList.copyOf(input));
    }

    /**
     * Constructor.
     *
     * @param input the input
     */
    public StringInput(List<String> input) {
        numRows = input.size();
        numColumns = input.get(0).length();
        initializeSquares();

        int row = 0;
        int column;

        for (String line : input) {
            column = 0;
            Preconditions.checkArgument(line.length() == numColumns, "Row %s has %s characters, expected %s.", row, line.length(), numColumns);
            for (char character : line.toCharArray()) {
                Square square = at(row, column);

                if (character == '#') {
                    square.setColor(Color.BLACK);
                } else if (character == '.') {
                    square.setColor(Color.WHITE);
                } else if (character >= '0' && character <= '9') {
                    int targetBlack = character - '0';
                    clues.add(new Clue(targetBlack, adjacent(row, column)));
                } else if (character == ' ') {
                    // Uncolored square, do nothing.
                } else {
                    Preconditions.checkArgument(false, "Illegal character '%s' at row %s, column %s.", character, row, column);
                }

                column++;
            }
            row++;
        }
    }
}
