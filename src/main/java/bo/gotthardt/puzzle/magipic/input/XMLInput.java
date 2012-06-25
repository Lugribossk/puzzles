package bo.gotthardt.puzzle.magipic.input;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import nu.xom.*;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.File;
import java.io.IOException;

/**
 * Abstract XML input reader.
 *
 * @author Bo Gotthardt
 */
public abstract class XMLInput extends GridInput {

    /**
     * Constructor.
     *
     * @param file the file to read
     * @param xpath the Xpath expression that specifies the list of nodes that corresponds to rows
     */
    public XMLInput(File file, String xpath) {
        try {
            XMLReader xmlreader = XMLReaderFactory.createXMLReader();
            // The XML documents refer to their DTD by a relative path, so turn off DTD loading as it is not important for this.
            xmlreader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

            Document doc = new Builder(xmlreader).build(file);
            Nodes rows = doc.query(xpath);

            numColumns = ImmutableList.copyOf(Splitter.on(" ").split(rows.get(0).getValue())).size();
            numRows = rows.size();
            initializeSquares();

            for (int i = 0; i < rows.size(); i++) {
                parseRow(rows.get(i), i);
            }
        } catch (IOException | ParsingException | SAXException e) {
            throw new IllegalStateException("There was a problem with the input file.", e);
        }
    }

    /**
     * Parse a specified node that corresponds to a row.
     *
     * @param rowData the node
     * @param row the row number
     */
    protected abstract void parseRow(Node rowData, int row);
}
