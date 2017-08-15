package parser;

import java.lang.reflect.Type;
import java.util.List;

/**
 * The Sheet Object encapsulates all information about a
 * sheet within the parsed Excel File. This includes the
 * sheet index, name, row values, headers, and a best
 * guess at the column types.
 *
 * @author meulmees
 *
 */

public class Sheet {

    private int sheetIndex;
    private String sheetName;
    private List<String> rowList;
    private List<String> headerList;
    private List<Type> columnTypes;

    public Sheet(String sheetName,
                 int sheetIndex,
                 List<Type> columnTypes,
                 List<String> headerList,
                 List<String> rowList) {
        this.sheetName = sheetName;
        this.sheetIndex = sheetIndex;
        this.columnTypes = columnTypes;
        this.headerList = headerList;
        this.rowList = rowList;
    }

    /**
     * Returns the Sheet Index of the Sheet Object.
     * <p>
     * 0 Indexed
     *
     * @return SheetIndex
     */
    public int getSheetIndex() {
        return sheetIndex;
    }
    protected void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    /**
     * Returns the Sheet Name of the Sheet Object.
     *
     * @return SheetName
     */
    public String getSheetName() {
        return sheetName;
    }
    protected void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    /**
     * Returns a list of comma separated Strings representing
     * the rows in the parsed excel file
     *
     * @return RowList
     */
    public List<String> getRowList() {
        return rowList;
    }
    protected void setRowList(List<String> valueList) {
        this.rowList = valueList;
    }

    /**
     * Returns a list of Strings which represent the headers
     * of the parsed excel file.
     * <p>
     * The first row with data is considered the Header Row.
     *
     * @return Headers
     */
    public List<String> getHeaderList() {
        return headerList;
    }
    protected void setHeaderList(List<String> headerList) {
        this.headerList = headerList;
    }

    /**
     * Returns the list of Java Types which is a best guess
     * for what the excel columns represent.
     *
     * @return Column Types
     */
    public List<Type> getColumnTypes() {
        return columnTypes;
    }
    protected void setColumnTypes(List<Type> columnTypes) {
        this.columnTypes = columnTypes;
    }

    /**
     * Returns the Array of Strings representing the cell values
     * at the specified row number.
     * <p>
     * Setting withQuotes to true will return the cellValues wrapped
     * in double quotes (which is how it is read from excel)
     *
     * @param rowNumber
     * @param withQuotes
     * @return CellValues
     */
    public String[] getCellValues(int rowNumber, boolean withQuotes) {
        String[] cellValues = getRowList().get(rowNumber).split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        if(withQuotes) {
            return cellValues;
        } else {
            String[] cellValues_ = new String[cellValues.length];
            int index = 0;
            for(String cellValue : cellValues) {
                cellValues_[index++] = cellValue.substring(1, cellValue.length()-1);
            }
            return cellValues_;
        }
    }
    /**
     * Returns the Array of Strings representing the cell values
     * at the specifid row number without wrapping the values in
     * double quotes.
     *
     * @param rowNumber
     * @return CellValues
     */
    public String[] getCellValues(int rowNumber) {
        return getCellValues(rowNumber, false);
    }
}
