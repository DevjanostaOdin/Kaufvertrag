package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.dataLayer.dataAccessObjects.IDao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VertragspartnerDaoSqlite implements IDao {
    @Override
    public Object create() {
        return null;
    }

    @Override
    public void create(Object objectToInsert) {

    }

    @Override
    public Object read(Object id) {

        // ----------------bullshit trial and error-------------------
        ConnectionManager conM = new ConnectionManager();
        conM.getNewConnection();
        try {
            // ein Objekt was immer eine Zeile enthält
            final ResultSet resultSet = conM.getExistingConnection().createStatement().executeQuery("SELECT * FROM NAME ");
            // .next() methode geht auf die nächste Zeile , und .next() liefert einen boolean Wert, ob es eine nächste Zeile gab
            while (resultSet.next()) {
                // Spalten auslesen : bei SQL start Index = 1
                System.out.println(resultSet.getString(1));
                // Spalten auslesen mit column Label
                System.out.println(resultSet.getString("Name"));

            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        //------------------------------------------------------

        return null;
    }

    @Override
    public List readAll() {
        return null;
    }

    @Override
    public void update(Object objectTpUpdate) {

    }

    @Override
    public void delete(Object id) {

    }
}
