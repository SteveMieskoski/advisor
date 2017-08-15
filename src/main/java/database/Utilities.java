package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.COMMA_SEP;

public class Utilities {

    public static ArrayList<String> getTableColumns(Connection connection, String tableName){

        try{
            ArrayList<String> columnArray = new ArrayList<>();
            Statement st= connection.createStatement();
            ResultSet rs=st.executeQuery("PRAGMA table_info(" + tableName +");");
            while(rs.next())
            {
                columnArray.add(rs.getString("name"));
                System.out.println(rs.getString("name"));
                System.out.println(rs.getString("type"));

            }

            return columnArray;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static String createInsertStatement(Connection connection, List<String> headers, String tableName){
        ArrayList<String> columns = Utilities.getTableColumns(connection, tableName);
        String sql = "INSERT INTO "+ tableName +"(";
        String excValues = ") VALUES (";
        int count = 0;
        if(columns != null){
            for(String h: headers){
                if(columns.contains(h)){
                    if(count == 0){
                        sql += "`" + h + "`";
                    } else {
                        sql += COMMA_SEP +"`" + h + "`";
                    }
                    count++;
                }
            }
            for(int i=0; i<count; i++){
                if(i == 0){
                    excValues += "?";
                } else {
                    excValues += ", ?";
                }
            }
            String result = sql + excValues + ");";
            return result;
        } else {
            return "";
        }
    }

    public static ArrayList<Integer> getColumnToHeaderRelations(Connection connection, List<String> headers, String tableName){
        ArrayList<String> columns = Utilities.getTableColumns(connection, tableName);
        ArrayList<Integer> relations = new ArrayList<>();
        if(columns != null){
            for(int i = 0; i<headers.size(); i++){
                if(columns.contains(headers.get(i))){
                    int idx = columns.indexOf(headers.get(i));
                    relations.add(i, idx);
                }
            }
        }

        return relations;
    }

}
