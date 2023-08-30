package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import java.sql.*;

public class ConnectionManager {

    private static final String CONNECTIONSTRING = "jdbc:sqlite:db.sqlite";
    private static Connection existingConnection;
    private static boolean classLoaded = false;

    public Connection getNewConnection(){
        Connection conn = null;
        if(!classLoaded) {
            try {
                Class.forName("org.sqlite.JDBC");
                conn =  DriverManager.getConnection(CONNECTIONSTRING); // baut verbindung auf
                classLoaded = true;
            } catch (SQLException  | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return conn;
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}