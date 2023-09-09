package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.exceptions.DaoException;

import java.sql.*;

public class ConnectionManager {

    private static final String CONNECTIONSTRING = "jdbc:sqlite:db.sqlite";
    private static Connection existingConnection;

    // classLoaded wird auch nicht genutzt. Laut google wird das für ältere Java Versionen benötigt, aber jetzt nicht mehr.
    private static boolean classLoaded = false;

    //CLASSNAME wird laut Klassendiagraqmm benötigt, aber nicht genutzt -> nachfragen wofür das da ist
    private static String CLASSNAME;

    public Connection getNewConnection() throws DaoException {
        return connect();
    }

    public Connection getExistingConnection() throws DaoException {
        if (existingConnection == null) {
            existingConnection = getNewConnection();
        }
        return existingConnection;
    }

    // close Methode vereinfacht mit einem einzigen Try Block um Code kürzer zu halten
    public void close(ResultSet resultSet, Statement statement, Connection connection) throws DaoException {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection to SQLite has been closed.");
            }
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Schließen der Datenbankverbindung: " + e.getMessage());
        }
    }

    // Url Variable kann direkt ersetzt werden durch CONNECTIONSTRING
    private Connection connect() throws DaoException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(CONNECTIONSTRING);
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Herstellen der Datenbankverbindung: " + e.getMessage());
        }
        return conn;
    }
}

