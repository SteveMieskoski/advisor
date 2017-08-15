package advisor;

//import database.ColumnParser;
import parser.Sheet;
import parser.XLSXParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AdvisorParse {

    public XLSXParser parser;
    public File file;
    public List<Sheet> sheetList;
    public ArrayList<String> contentList;
    //private ColumnParser columnParser;
    private int numberOfSheets;
    private Sheet single;
    private ArrayList<String> rowsForSingle;
    private ArrayList<String> headersForSingle;


    public AdvisorParse(String path){
        file = new File(path);
        parser = new XLSXParser(file);
    }


    public List<Sheet> getSheetList(){
        return this.sheetList;
    };

    public int getNumberOfSheets(){
        return this.numberOfSheets;
    }

   public ArrayList<String> getRowsForSingle(){
       return this.rowsForSingle;
   }

   public ArrayList<String> getHeadersForSingle(){
       return this.headersForSingle;
   }



    public void process(){
        parser.process(true, true);
        sheetList = parser.getSheetList();
        numberOfSheets = sheetList.size();
        if(sheetList.size() == 1){
            single = sheetList.get(0);
            rowsForSingle = (ArrayList<String>) single.getRowList();
            headersForSingle = (ArrayList<String>) single.getHeaderList();
        } else {
            single = null;
            rowsForSingle = null;
        }
        System.out.println("number of sheets = " + Integer.toString(sheetList.size()));

      /*  for(Sheet s: sheetList){
           contentList =  s.getRowList();
        }*/

       //columnParser = new ColumnParser(sheetList.get(0).getHeaderList(), sheetList);
      //  System.out.println(sheetList.get(0).getCellValues(0)[columnParser.symbolIdx]);
/*        for(Sheet s: sheetList){

           for(String str: s.getHeaderList()){
               System.out.println(str);
           }
        }

        System.out.println(contentList.get(0));*/
        /*for(String s: contentList){
            System.out.println(s);
        }*/
    }
}
