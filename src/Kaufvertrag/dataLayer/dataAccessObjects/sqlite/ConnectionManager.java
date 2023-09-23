package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.exceptions.DaoException;

import java.sql.*;

public class ConnectionManager {
    private static final String CONNECTIONSTRING = "jdbc:sqlite:db.sqlite";
    private static Connection existingConnection;

    private static boolean classLoaded = false;
    private static final String CLASSNAME = "org.sqlite.JDBC";

    public ConnectionManager() throws DaoException {
        if (!classLoaded) {
            try {
                Class.forName(CLASSNAME);
                classLoaded = true;
            } catch (ClassNotFoundException e) {
                throw new DaoException("Der SQLite JDBC Treiber kann nicht geladen werden.");
            }
        }
    }

    public Connection getNewConnection() throws DaoException {
        return connect();
    }

    public Connection getExistingConnection() throws DaoException {
        if (existingConnection == null) {
            existingConnection = getNewConnection();
        }
        return existingConnection;
    }

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


