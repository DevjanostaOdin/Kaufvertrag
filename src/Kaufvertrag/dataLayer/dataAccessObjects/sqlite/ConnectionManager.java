package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import java.sql.*;

public class ConnectionManager {

    // JDBC URL - zu welchem Datenbanktyp wollen wir uns verbinden = jdbc:sqlite

    private static final String CONNECTIONSTRING = "jdbc:sqlite:db.sqlite";
    private static Connection existingConnection;
    private static boolean classLoaded = false;

    public Connection getNewConnection(){
        return connect();
    }

    public Connection getExistingConnection(){
        if (existingConnection == null){
            existingConnection = getNewConnection();
        }
        return existingConnection;
    }

    public void close(ResultSet resultset, Statement statement, Connection connection) {
        try {
            if (resultset != null) {
                resultset.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection to SQLite has been closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = CONNECTIONSTRING;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            return conn;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}

