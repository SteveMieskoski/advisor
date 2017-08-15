package parser;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XLSXWorkSheetHandler extends DefaultHandler {

    /**
     * The type of the data value is indicated by an attribute on
     * the cell element; the value is in a "v" element within the cell.
     */
    enum xssfDataType {
        BOOL,
        ERROR,
        FORMULA,
        INLINESTR,
        SSTINDEX,
        NUMBER,
    }

    private StylesTable stylesTable;
    private ReadOnlySharedStringsTable sharedStringsTable;
    private boolean vIsOpen;
    private xssfDataType nextDataType;
    private short formatIndex;
    private String formatString;
    private final DataFormatter formatter;
    private int thisColumn = -1;
    private int lastColumnNumber = -1;
    private StringBuffer value;

    private StringBuilder objCurrentRow = new StringBuilder();
    private List<String> valueList;
    private List<String> headerList;
    private List<Type> columnTypes;
    private boolean settingTypes = false;
    private int typesSet = 0;
    private boolean[] skipSet;

    private boolean ignoreBlankRows = true;
    private boolean useCellFormatting = true;

    /**
     * Accepts objects needed while parsing.
     *
     * @param styles Table of styles
     * @param strings Table of shared strings
     *
     */
    public XLSXWorkSheetHandler(StylesTable styles, ReadOnlySharedStringsTable strings,
                                boolean ignoreBlankRows, boolean useCellFormatting) {
        this.stylesTable = styles;
        this.sharedStringsTable = strings;
        this.value = new StringBuffer();
        this.nextDataType = xssfDataType.NUMBER;
        this.formatter = new DataFormatter();

        this.valueList = new ArrayList<String>();
        this.headerList = new ArrayList<String>();
        this.columnTypes = new ArrayList<Type>();

        this.ignoreBlankRows = ignoreBlankRows;
        this.useCellFormatting = useCellFormatting;
    }

    public List<String> getValueList() {
        return valueList;
    }

    public List<String> getHeaderList() {
        return headerList;
    }

    public List<Type> getColumnTypes() {
        return columnTypes;
    }

    /**
     * (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String,java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(String uri, String localName, String name,
                             Attributes attributes) throws SAXException {

        if ("inlineStr".equals(name) || "v".equals(name)) {
            vIsOpen = true;
            // Clear contents cache
            value.setLength(0);
        }
        // c => cell
        else if ("c".equals(name)) {
            // Get the cell reference
            String r = attributes.getValue("r");
            int firstDigit = -1;
            for (int c = 0; c < r.length(); ++c) {
                if (Character.isDigit(r.charAt(c))) {
                    firstDigit = c;
                    break;
                }
            }
            thisColumn = nameToColumn(r.substring(0, firstDigit));

            // Set up defaults.
            this.nextDataType = xssfDataType.NUMBER;
            this.formatIndex = -1;
            this.formatString = null;
            String cellType = attributes.getValue("t");
            String cellStyleStr = attributes.getValue("s");
            if ("b".equals(cellType))
                nextDataType = xssfDataType.BOOL;
            else if ("e".equals(cellType))
                nextDataType = xssfDataType.ERROR;
            else if ("inlineStr".equals(cellType))
                nextDataType = xssfDataType.INLINESTR;
            else if ("s".equals(cellType))
                nextDataType = xssfDataType.SSTINDEX;
            else if ("str".equals(cellType))
                nextDataType = xssfDataType.FORMULA;
            else if (cellStyleStr != null) {
				/*
				 * It's a number, but possibly has a style and/or special format.
				 * should use org.apache.poi.ss.usermodel.BuiltinFormats,
				 * and I see javadoc for that at apache.org, but it's not in the
				 * POI 3.5 Beta 5 jars.  Scheduled to appear in 3.5 beta 6.
				 */
                int styleIndex = Integer.parseInt(cellStyleStr);
                XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
                this.formatIndex = style.getDataFormat();
                this.formatString = style.getDataFormatString();
                if (this.formatString == null)
                    this.formatString = BuiltinFormats.getBuiltinFormat(this.formatIndex);
            }
        }

    }

    /**
     * (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    public void endElement(String uri, String localName, String name)
            throws SAXException {

        String thisStr = "";
        boolean skipColumn = false;

        // v => contents of a cell
        if ("v".equals(name)) {
            if(this.headerList.size() > 0 && this.getValueList().size() == 0) {
                settingTypes = true;
                //If we're setting types and we skipped a column assume its a String column
                if((thisColumn-1) != lastColumnNumber) {
                    for(int i=0; i<((thisColumn) - (lastColumnNumber + 1)); i++) {
                        if(!skipSet[i]) {
                            columnTypes.add(String.class);
                            typesSet++;
                        }
                    }
                }
            } else {
                settingTypes = false;
                for(int i=0;i<(headerList.size()-typesSet); i++) {
                    columnTypes.add(String.class);
                    typesSet++;
                }
            }

            if(!skipColumn) {
                // Process the value contents as required.
                // Do now, as characters() may be called more than once
                switch(nextDataType) {

                    case BOOL:
                        char first = value.charAt(0);
                        thisStr = first == '0' ? "FALSE" : "TRUE";
                        if(settingTypes) {
                            this.columnTypes.add(boolean.class);
                            typesSet++;
                        }
                        break;

                    case ERROR:
                        thisStr = "\"ERROR:" + value.toString() + '"';
                        if(settingTypes) {
                            this.columnTypes.add(Exception.class);
                            typesSet++;
                        }
                        break;

                    case FORMULA:
                        // A formula could result in a string value,
                        // so always add double-quote characters.
                        thisStr = '"' + value.toString() + '"';
                        if(settingTypes) {
                            this.columnTypes.add(String.class);
                            typesSet++;
                        }
                        break;

                    case INLINESTR:
                        // TODO: have seen an example of this, so it's untested.
                        XSSFRichTextString rtsi = new XSSFRichTextString(value.toString());
                        thisStr = '"' + rtsi.toString() + '"';
                        if(settingTypes){
                            if(isDate(thisStr))
                                this.columnTypes.add(Date.class);
                            else
                                this.columnTypes.add(String.class);
                            typesSet++;
                        }
                        break;

                    case SSTINDEX:
                        String sstIndex = value.toString();
                        try {
                            int idx = Integer.parseInt(sstIndex);
                            XSSFRichTextString rtss = new XSSFRichTextString(sharedStringsTable.getEntryAt(idx));
                            thisStr = '"' + rtss.toString() + '"';
                            if(settingTypes){
                                if(isDate(thisStr))
                                    this.columnTypes.add(Date.class);
                                else
                                    this.columnTypes.add(String.class);
                                typesSet++;
                            }
                        }
                        catch (NumberFormatException ex) {
                            System.err.println("Failed to parse SST index '"+sstIndex+"': "+ex.getLocalizedMessage()+"\n"+ex.getStackTrace());
                        }
                        break;

                    case NUMBER:
                        String n = value.toString();
                        if (this.formatString != null) {
                            if(useCellFormatting) {
                                thisStr = "'" + formatter.formatRawCellContents(Double.parseDouble(n), this.formatIndex, this.formatString) + "'";
                                thisStr = thisStr.replaceAll("[^-/.,\\d][\\s]", "");
                            } else {
                                thisStr = "'" + thisStr + "'";
                            }
                            if(settingTypes) {
                                if(isDate(thisStr))
                                    this.columnTypes.add(Date.class);
                                else if(thisStr.contains("."))
                                    this.columnTypes.add(Double.class);
                                else
                                    this.columnTypes.add(Integer.class);
                                typesSet++;
                            }
                        } else {
                            thisStr = n;
                            if(settingTypes) {
                                if(thisStr.contains("\\."))
                                    this.columnTypes.add(Double.class);
                                else
                                    this.columnTypes.add(Integer.class);
                                typesSet++;
                            }
                        }
                        break;

                    default:
                        thisStr = '"'+value.toString()+'"';
                        break;
                }

                // Output after we've seen the string contents
                // Emit commas for any fields that were missing on this row
                if(lastColumnNumber == -1) { lastColumnNumber = 0; }
                for (int i = lastColumnNumber; i < thisColumn; ++i) {
                    if(headerList.size() > 0) {
                        if(!skipSet[lastColumnNumber]) {
                            objCurrentRow.append(",");
                        }
                    } else {
                        objCurrentRow.append(",");
                    }
                }

                // Might be the empty string.
                objCurrentRow.append(thisStr);

                // Update column
                if(thisColumn > -1)
                    lastColumnNumber = thisColumn;
            }
        }
        else if("row".equals(name)) {
            // We're onto a new row

            int emptyTrailingColumns = this.headerList.size() - this.columnTypes.size();
            for(int i=0; i<emptyTrailingColumns; i++) {
                this.columnTypes.add(String.class);
            }

            String[] cellValues = objCurrentRow.toString().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
            if(headerList.size() > cellValues.length) {
                int size = cellValues.length;
                for(int i=0; i<headerList.size()-size; i++) {
                    if(objCurrentRow.toString().equals(""))
                        objCurrentRow.append("\"\"");
                    objCurrentRow.append(",\"\"");
                }
            }

            //I don't want to include empty rows
            if(!isRowBlank(objCurrentRow.toString())) {
                String tmp = objCurrentRow.toString();
                if(tmp.substring(tmp.length()-1).equals(""))
                    objCurrentRow = new StringBuilder(tmp.substring(0, tmp.length()-1));
                //If both the valueList and headerList are empty then the current row
                //will be considered the header row
                if(this.valueList.size() == 0 && this.headerList.size() == 0) {
                    String[] headers = objCurrentRow.toString().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    skipSet = new boolean[headers.length];
                    for(String header : headers) {
                        header = StringUtils.remove(header, '"');
                        this.headerList.add(header);
                    }
                } else {
                    this.valueList.add(objCurrentRow.toString());
                }
                objCurrentRow = new StringBuilder();
            }

            lastColumnNumber = -1;
        }
    }

    private boolean isRowBlank(String rowData) {
        if(!ignoreBlankRows)
            return false;

        String[] values = rowData.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        for(String value : values) {
            value = value.replaceAll("\"", "");
            if(value.length() > 0)
                return false;
        }
        return true;
    }

    /**
     * Captures characters only if a suitable element is open.
     * Originally was just "v"; extended for inlineStr also.
     */
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if (vIsOpen)
            value.append(ch, start, length);
    }

    /**
     * Converts an Excel column name like "C" to a zero-based index.
     * @param name
     * @return Index corresponding to the specified name
     */
    private int nameToColumn(String name) {
        int column = -1;
        for (int i = 0; i < name.length(); ++i) {
            int c = name.charAt(i);
            column = (column + 1) * 26 + c - 'A';
        }
        return column;
    }

    private boolean isDate(String thisStr) {
        thisStr = StringUtils.remove(thisStr, '"');
        boolean result = false;
        if(thisStr.split("\\.").length == 3) {
            for(String element : thisStr.split("\\.")) {
                result = isInteger(element);
            }
        }
        if(thisStr.split("-").length == 3) {
            for(String element : thisStr.split("-")) {
                result = isInteger(element);
            }
        }
        if(thisStr.split("/").length == 3) {
            for(String element : thisStr.split("/")) {
                result = isInteger(element);
            }
        }
        return result;
    }

    private boolean isInteger(String thisStr) {
        try {
            Integer.parseInt(thisStr);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}
