package bo.gotthardt.puzzle.magipic.input;

import bo.gotthardt.puzzle.magipic.Clue;
import com.google.common.base.Splitter;
import nu.xom.Node;

import java.io.File;

/**
 * XML input reader for clues.
 *
 * @author Bo Gotthardt
 */
public class XMLClueInput extends XMLInput {
    /**
     * Constructor.
     *
     * @param file the file to read
     */
    public XMLClueInput(File file) {
        super(file, "/puzzle/data/source/row");
    }

    @Override
    protected void parseRow(Node rowData, int row) {
        int column = 0;
        for (String rawClue : Splitter.on(" ").split(rowData.getValue())) {
            int clue = Integer.parseInt(rawClue);
            if (clue >= 0 && clue <= 9) {
                clues.add(new Clue(clue, adjacent(row, column)));
            }

            column++;
        }
    }
}
