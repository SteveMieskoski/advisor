package sqlite.connect;

import database.Constants;

import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

public class SQLiteConnector extends Constants {
    private Connection connection;
    private String dbName;
    private String dbPath;
    private String dbUri;

    public SQLiteConnector() {
    }

    public SQLiteConnector(String databaseName) {
        this.dbName = databaseName;
        dbUri = "jdbc:sqlite:/media/sysadmin/projects/Java/0Learn/advisor/db/" + this.dbName;
    }

    public SQLiteConnector(String databaseName, String databasePath){
        this.dbName = databaseName;
        this.dbUri = "jdbc:sqlite:" + databasePath + this.dbName;
    }

    public SQLiteConnector(String databaseName, boolean full){
        this.dbName = databaseName;

    }

    public Connection getConnection() {
        return this.connection;
    }



    public Connection createNewDatabase() {
        dbUri = "jdbc:sqlite:/media/sysadmin/projects/Java/0Learn/advisor/db/" + this.dbName;

        try (Connection connect = DriverManager.getConnection(dbUri)) {
            if (connect != null) {

                this.connection = connect;
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta);
                System.out.println("A new database has been created.");
                return connection;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public void createNewTable() {

    }

    public void setDatabaseName(String filename) {
        this.dbName = filename;
    }

    public void setDatabasePath(String filedir) {
        this.dbPath = filedir;
    }

    public void setDatabase(String dburi) {
        this.dbUri = "jdbc:sqlite:" + dburi;
    }

    public Connection connect() {
        try {
            if (this.dbUri != null) {
                connection = null;

               // checkDbPath();

                connection = DriverManager.getConnection(this.dbUri);
                System.out.println("Connected to SQLite Database");
                return connection;


            } else {
                throw new SQLException("No Path for Connection");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void reConnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;

                checkDbPath();

                connection = DriverManager.getConnection(dbUri);
                System.out.println("Connected to SQLite Database");


            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }


    public void createTable() {

        String sql = CREATE_NOT + TABLE_NAME + " (" +
                "id" + INTEGER + " PRIMARY KEY AUTOINCREMENT, " +
                SYMBOL + TEXT + COMMA_SEP +
                COMPANY + TEXT + COMMA_SEP +
                ALL_US + TEXT + COMMA_SEP +
                BUY_LEAST_ONE + TEXT + COMMA_SEP +
                GIC_SECTOR + TEXT + COMMA_SEP +
                GIC_INDUSTRY + TEXT + COMMA_SEP +
                DIVIDEND_YIELD + REAL + COMMA_SEP +
                PEG_STR + REAL + COMMA_SEP +
                CHNG_VS_SP500 + REAL + COMMA_SEP +
                CREDIT_SUISSE + TEXT + COMMA_SEP +
                MORNINGSTAR_CURRENT + TEXT + COMMA_SEP +
                WELLS_FARGO_CURRENT + TEXT + COMMA_SEP +
                MORNINGSTART_QUANT + TEXT + COMMA_SEP +
                GOLDEN_CORE + INTEGER + COMMA_SEP +
                GOLDEN_GROWTH + INTEGER + COMMA_SEP +
                GOLDEN_VALUE + INTEGER +
                ");";

        try {
            Statement stmt = this.connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    private String checkDbPath() {
        try {
            if (dbPath != null && dbName != null && dbUri == null) {
                return "jdbc:sqlite:" + dbPath + dbName;
            } else if (dbUri == null && dbName != null) {
                String basePath = Paths.get(".").toAbsolutePath().normalize().toString();
                return "jdbc:sqlite:" + basePath + dbName;
            } else if (dbUri != null) {
                return dbUri;
            } else {
                throw new SQLException("No Database identified");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            String basePath = Paths.get(".").toAbsolutePath().normalize().toString();
            System.out.println("Falling back to default.db");
            System.out.println("Using path: " + basePath + "/db/default.db");
            return "jdbc:sqlite:" + basePath + "/db/default.db";
        }
    }

}
