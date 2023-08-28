package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
                                                // WIR HATTEN IDAO OHNE <..., ...> DAHER HATTEN WIR OBJECT TRALALA
public class VertragspartnerDaoSqlite implements IDao<IVertragspartner, String> {


    @Override // FRAGEN WAS WAR DIE IDEE DAHINTER
    public IVertragspartner create() {
        return null;
    }

    @Override   // HIER HATTEN WIR NICHT IVERTRAGSPARTNER SONDERN OBJECT ...
    public void create(IVertragspartner objectToInsert) {

        ConnectionManager conMan = new ConnectionManager();
        Connection connection = conMan.getNewConnection();
        String sqlTemplate = "INSERT INTO" +
                " VERTRAGSPARTNER (AUSWEIS_NR, VORNAME, NACHNAME) " +
                " VALUES(\"%s\", \"%s\", \"%s\")";

        String sql = String.format(sqlTemplate, objectToInsert.getAusweisNr(), objectToInsert.getVorname(), objectToInsert.getNachname());
        System.out.println(sql);

        // Exception klasse nutzen!
        try {
            connection.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IVertragspartner read(String id) {
        return null;
    }

    @Override
    public List<IVertragspartner> readAll() {
        return null;
    }

    @Override
    public void update(IVertragspartner objectTpUpdate) {
    }

    @Override
    public void delete(String id) {

    }
}
