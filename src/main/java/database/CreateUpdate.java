package database;

import parser.Sheet;
import sqlite.connect.SQLiteConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CreateUpdate extends Constants {


    public List<String> headers;
    public int symbolIdx;
    public int companyIdx;
    public int allUSIdx;
    public int buyOneIdx;
    public int sectorsIdx;
    public int industriesIdx;
    public int divdYieldIdx;
    public int pegIdx;
    public int chgSP500;
    public int CrSuCurr;
    public int morningCurrIdx;
    public int moriningQIdx;
    public int wellsIdx;
    public int qcIdx;
    public int qgIdx;
    public int qvIdx;
    public int MarketPrice;
    public int Category;
    public int TechAttribScore;
    public int TrendChartColumn;
    public int PFTrend;
    public int PFSignal;
    public int RSSignal;
    public int RSColumn;
    public int PeerRSSignal;
    public int PeerRSColumn;
    public int WeeklyMomentum;
    public int WeeklyDistribution;
    public int MA200Day;
    public int VertPriceObj;
    public int RewardRisk;
    public int Yield;
    public int Optionable;
    public int UserNote;
    public int DWASector;

    private List<String> rowList;
    private SQLiteConnector conn;
    private Connection connection;

    public CreateUpdate(List<String> headers) {
        this.headers = headers;
        getColumnIndex();
    }

    public CreateUpdate(Connection connection, List<String> headers) {
        this.connection = connection;
        this.headers = headers;
        //  this.rowList = sheetList;
        getColumnIndex();
    }

    public void getColumnIndex() {
       /* System.out.println("check: " + headers.toString());
        this.symbolIdx = headers.indexOf("Symbol");
        this.companyIdx = headers.indexOf("Company");
        this.allUSIdx = headers.indexOf("All U.S. equities");
        this.buyOneIdx = headers.indexOf("Buy rated by at least one fundamental provider");
        this.sectorsIdx = headers.indexOf("GICs Sectors");
        this.industriesIdx = headers.indexOf("GICs Industries");
        this.divdYieldIdx = headers.indexOf("Dividend yield (TTM)");
        this.pegIdx = headers.indexOf("PEG");
        this.chgSP500 = headers.indexOf("Price change YTD vs S&P 500");
        this.CrSuCurr = headers.indexOf("Credit Suisse - current rating");
        this.morningCurrIdx = headers.indexOf("Morningstar - current rating");
        this.wellsIdx = headers.indexOf("Wells Fargo Securities - current rating");
        this.moriningQIdx = headers.indexOf("Morningstar Quant - valuation");
        this.qcIdx = headers.indexOf("Golden Capital Quant Score - core");
        this.qgIdx = headers.indexOf("Golden Capital Quant Score - growth");
        this.qvIdx = headers.indexOf("Golden Capital Quant Score - value");
        this.MarketPrice = headers.indexOf("Market Price");
        this.Category = headers.indexOf("Category");
        this.TechAttribScore = headers.indexOf("Tech Attrib / Score");
        this.TrendChartColumn = headers.indexOf("Trend Chart Column");
        this.PFTrend = headers.indexOf("PF Trend");
        this.PFSignal = headers.indexOf("P&F Signal");
        this.RSSignal = headers.indexOf("RS Signal");
        this.RSColumn = headers.indexOf("RS Column");
        this.PeerRSSignal = headers.indexOf("Peer RS Signal");
        this.PeerRSColumn = headers.indexOf("Peer RS Column");
        this.WeeklyMomentum = headers.indexOf("Weekly Momentum");
        this.WeeklyDistribution = headers.indexOf("Weekly Distribution");
        this.MA200Day = headers.indexOf("200 Day MA");
        this.VertPriceObj = headers.indexOf("Vert Price Obj");
        this.RewardRisk = headers.indexOf("Reward/Risk");
        this.Yield  = headers.indexOf("Yield");
        this.Optionable  = headers.indexOf("Optionable");
        this.UserNote  = headers.indexOf("UserNote");
        this.DWASector = headers.indexOf("DWA Sector");
        System.out.println(symbolIdx);*/
    }


    public void insertIterate(ArrayList<String> rowList) {
        int count = rowList.size();
        for (int i = 0; i < count; i++) {
            String[] splitRowString = rowList.get(i).split(",");
            insertRow(splitRowString);
        }
    }




    public void insertRow(String[] row) {
        String sql = Utilities.createInsertStatement(connection, headers, "all_stocks");
        System.out.println(sql);
        try {
            ArrayList<Integer> relations = Utilities.getColumnToHeaderRelations(connection, headers, "all_stocks");
            PreparedStatement pstmt = connection.prepareStatement(sql);
            for(int i = 0; i<relations.size(); i++){
                pstmt.setString(relations.get(i), row[i]);
            }
            int rowsChanged = pstmt.executeUpdate();
            if (rowsChanged > 0) {
                System.out.println("row inserted successfully");
            } else {
                System.out.println("row add failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
