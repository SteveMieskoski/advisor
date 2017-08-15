package parser;

import java.io.File;
import java.util.List;

import parser.Sheet;

public abstract class ExcelParser {

    public static final int READ_ALL = -1;

    public abstract List<Sheet> getSheetList();

    public abstract void process() throws RuntimeException;
    public abstract void process(int sheetIndex) throws RuntimeException;
    public abstract void process(boolean ignoreBlankRows, boolean useCellFormatting) throws RuntimeException;
    public abstract void process(boolean ignoreBlankRows, boolean useCellFormatting, int sheetIndex) throws RuntimeException;

    /**
     * Creates an Parser to read an XLSX File.
     *
     * @param xlsxFile
     * @return ExcelParser
     */
    public static XLSXParser createXLSXParser(File xlsxFile) {
        return new XLSXParser(xlsxFile);
    }
}
