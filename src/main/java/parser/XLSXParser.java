package parser;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.model.StylesTable;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XLSXParser extends ExcelParser {

    private File xlsxFile;
    private OPCPackage xlsxPackage;
    private List<Sheet> sheetList;

    public XLSXParser(File xlsxFile) {
        this.xlsxFile = xlsxFile;
        this.sheetList = new ArrayList<Sheet>();
    }

    /**
     * Returns the List of Sheets in the parsed Excel File which contains
     * some data.
     * <p>
     * <b>NOTE:</b> You first need to invoke one of the ExcelParser.process() methods
     * to parse the Excel File.
     *
     * @return List<Sheet>
     */
    @Override
    public List<Sheet> getSheetList() {
        return this.sheetList;
    }

    /**
     * Read the Excel File into memory, by default blank rows are
     * ignored and cell formatting is applied
     *
     * @throws RuntimeException
     */
    @Override
    public void process() throws RuntimeException {
        process(true, true);
    }

    /**
     * Read the specified Sheet Index into memory, by default blank
     * rows are ignored and cell formatting is applied
     *
     * @param sheetIndex
     * @throws RuntimeException
     */
    @Override
    public void process(int sheetIndex) throws RuntimeException {
        process(true, true, sheetIndex);
    }

    /**
     * Read the Excel File into memory specifying whether you want to include
     * blank rows, and whether you want to apply any cell formatting which
     * has been specified in the excel file.
     *
     * @param ignoreBlankRows
     * @param useCellFormatting
     * @throws RuntimeException
     */
    @Override
    public void process(boolean ignoreBlankRows, boolean useCellFormatting) throws RuntimeException {
        try {
            this.xlsxPackage = OPCPackage.open(xlsxFile.getPath(), PackageAccess.READ);
            read(READ_ALL, ignoreBlankRows, useCellFormatting);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        } finally {
            if(null != this.xlsxPackage) {
                try {
                    this.xlsxPackage.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Read the specified Sheet Index Excel File into memory specifying whether
     * you want to include blank rows, and whether you want to apply any cell
     * formatting which has been specified in the excel file.
     *
     * @param ignoreBlankRows
     * @param useCellFormatting
     * @param sheetIndex
     * @throws RuntimeException
     */
    @Override
    public void process(boolean ignoreBlankRows, boolean useCellFormatting, int sheetIndex) throws RuntimeException {
        try {
            this.xlsxPackage = OPCPackage.open(xlsxFile.getPath(), PackageAccess.READ);
            read(sheetIndex, ignoreBlankRows, useCellFormatting);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        } finally {
            if(null != this.xlsxPackage) {
                try {
                    this.xlsxPackage.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void read(int sheetNum,
                      boolean ignoreBlankRows, boolean useCellFormatting) throws RuntimeException {
        try {
            ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
            XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
            StylesTable styles = xssfReader.getStylesTable();
            XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
            int index = 0;
            while (iter.hasNext()) {
                InputStream stream = iter.next();
                if ((READ_ALL == sheetNum) || (index == sheetNum)) {
                    String sheetName = iter.getSheetName();
                    readSheet(index, styles, strings, stream, sheetName, ignoreBlankRows, useCellFormatting);
                    stream.close();
                }
                ++index;
            }
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void readSheet(int index, StylesTable styles, ReadOnlySharedStringsTable strings,
                           InputStream sheetInputStream, String sheetName,
                           boolean ignoreBlankRows, boolean useCellFormatting) throws RuntimeException {
        XLSXWorkSheetHandler contentHandler = null;
        try {
            InputSource sheetSource = new InputSource(sheetInputStream);
            SAXParserFactory saxFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxFactory.newSAXParser();
            XMLReader sheetParser = saxParser.getXMLReader();
            contentHandler = new XLSXWorkSheetHandler(styles, strings, ignoreBlankRows, useCellFormatting);
            sheetParser.setContentHandler(contentHandler);
            sheetParser.parse(sheetSource);

        } catch(RuntimeException e) {
            throw new RuntimeException(e);
        } catch(OutOfMemoryError e) {
            System.out.println("Reached Maximum Allowed Memory Usage: ArrayList size "+
                    contentHandler.getValueList().size()+" on Sheet"+index+" - "+sheetName);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }

        if(contentHandler.getValueList().size() > 0) {
            this.sheetList.add(new Sheet(sheetName, index,
                    contentHandler.getColumnTypes(),
                    contentHandler.getHeaderList(),
                    contentHandler.getValueList()));
        }
    }

}
