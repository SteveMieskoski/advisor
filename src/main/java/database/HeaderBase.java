package database;

import java.util.Hashtable;
import java.util.List;

public class HeaderBase {

    public Hashtable<String, Integer> table;

    String[] headers = new String[]{"Symbol",
            "Company",
            "All U.S. equities",
            "Buy rated by at least one fundamental provider",
            "GICs Sectors",
            "GICs Industries",
            "Dividend yield (TTM)",
            "PEG",
            "Price change YTD vs S&P 500",
            "Credit Suisse - current rating",
            "Morningstar - current rating",
            "Wells Fargo Securities - current rating",
            "Morningstar Quant - valuation",
            "Golden Capital Quant Score - core",
            "Golden Capital Quant Score - growth",
            "Golden Capital Quant Score - value",
            "Market Price",
            "Category",
            "Tech Attrib / Score",
            "Trend Chart Column",
            "PF Trend",
            "P&F Signal",
            "RS Signal",
            "RS Column",
            "Peer RS Signal",
            "Peer RS Column",
            "Weekly Momentum",
            "Weekly Distribution",
            "200 Day MA",
            "Vert Price Obj",
            "Reward/Risk",
            "Yield",
            "Optionable",
            "UserNote",
            "DWA Sector"};


    public void createMap(String[] keys){
        table = new Hashtable<>();

        for(String k: keys){
            table.put(k, -1);
        }
    }

    public void getColumnIndex(List<String> headers) {
        for(String h: headers){
            if(table.containsKey(h)){
                table.put(h, headers.indexOf(h));
            }
        }
    }

}
