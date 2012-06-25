package bo.gotthardt.puzzle.magipic.input;

import bo.gotthardt.puzzle.magipic.Color;
import com.google.common.base.Splitter;
import nu.xom.Node;

import java.io.File;

/**
 * XML input reader for a solved puzzle.
 *
 * @author Bo Gotthardt
 */
public class XMLSolvedInput extends XMLInput {
    /**
     * Constructor.
     *
     * @param file the file to read
     */
    public XMLSolvedInput(File file) {
        super(file, "/puzzle/data/solution/row");
    }

    @Override
    protected void parseRow(Node rowData, int row) {
        int column = 0;
        for (String rawClue : Splitter.on(" ").split(rowData.getValue())) {
            int state = Integer.parseInt(rawClue);
            if (state == 1) {
                at(row, column).setColor(Color.BLACK);
            } else if (state == 0) {
                at(row, column).setColor(Color.WHITE);
            }

            column++;
        }
    }
}
