package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Query extends Constants {

    private Connection connection;

    public Query(Connection connection) {
        this.connection = connection;
    }

    private ArrayList<Stock> resultArray(ResultSet rs){
        try{
            ArrayList<Stock> stocks = new ArrayList<>();
            while (rs.next()) {
                Stock stock = new Stock(rs.getString(SYMBOL),
                        rs.getString(COMPANY), rs.getString(ALL_US), rs.getString(BUY_LEAST_ONE),
                        rs.getString(GIC_SECTOR), rs.getString(GIC_INDUSTRY), rs.getString(DIVIDEND_YIELD),
                        rs.getDouble(PEG_STR), rs.getDouble(CHNG_VS_SP500), rs.getString(CREDIT_SUISSE),
                        rs.getString(MORNINGSTAR_CURRENT), rs.getString(WELLS_FARGO_CURRENT),
                        rs.getString(MORNINGSTART_QUANT), rs.getInt(GOLDEN_CORE), rs.getInt(GOLDEN_GROWTH),
                        rs.getInt(GOLDEN_VALUE));
                stocks.add(stock);
            }
            return stocks;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }

    }


    public ArrayList<Stock> selectAll() {
        String sql = "SELECT * FROM " + TABLE_NAME;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            return resultArray(rs);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }


    public ArrayList<Stock> getSome(){
        return new ArrayList<>();
    }


}
