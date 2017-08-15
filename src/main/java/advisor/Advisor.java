package advisor;

import database.CreateUpdate;
import parser.Sheet;
import parser.XLSXParser;
import sqlite.connect.SQLiteConnector;
import sqlite.connect.SQLiteConnector.*;

import java.io.File;
import java.sql.Connection;
import java.util.List;

public class Advisor {
    SQLiteConnector conn;
    Connection connection;

    public Advisor(){
        connectToDB();
    }

    public void start(){
        String path = "/media/sysadmin/projects/Java/0Learn/example_data/wellsDuplicates.xlsx";
        AdvisorParse advisorParse = new AdvisorParse(path);
        advisorParse.process();
        if(advisorParse.getNumberOfSheets() == 1){
            conn = new SQLiteConnector("demo.db");
            conn.connect();
            conn.createTable();
            connection = conn.getConnection();
           CreateUpdate actions = new CreateUpdate(connection, advisorParse.getHeadersForSingle());
            actions.insertIterate(advisorParse.getRowsForSingle());
        }

    }


    private void connectToDB(){

    }






}
