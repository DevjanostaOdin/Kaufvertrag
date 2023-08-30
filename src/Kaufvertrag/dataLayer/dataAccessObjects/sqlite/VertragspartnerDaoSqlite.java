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

        Connection connection = getConnection();
        String sqlTemplatePerson = "INSERT INTO" +
                " VERTRAGSPARTNER (AUSWEIS_NR, VORNAME, NACHNAME) " +
                " VALUES(\"%s\", \"%s\", \"%s\")";

        String sqlTemplateAdresse = "INSERT INTO" +
                " ADRESSE (STRASSE, HAUSNUMMER, PLZ, ORT) " +
                " VALUES(\"%s\",\"%s\", \"%s\", \"%s\")";



        String sqlPerson = String.format(sqlTemplatePerson, objectToInsert.getAusweisNr(), objectToInsert.getVorname(), objectToInsert.getNachname());

        String sqlAdresse = String.format(sqlTemplateAdresse, objectToInsert.getAdresse().getStrasse(),
                objectToInsert.getAdresse().getHausNr(), objectToInsert.getAdresse().getPlz(), objectToInsert.getAdresse().getOrt());

        System.out.println(sqlPerson);
        System.out.println(sqlAdresse);




        // Exception klasse nutzen!
        try {
            connection.createStatement().executeUpdate(sqlPerson);
            connection.createStatement().executeUpdate(sqlAdresse);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     private Connection getConnection() {
         ConnectionManager conMan = new ConnectionManager();
         Connection connection = conMan.getNewConnection();
         return connection;
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
        Connection connection = getConnection();
        String sqlDelete = "DELETE " +
                "FROM " + id;
        System.out.println(sqlDelete);
        try {
            connection.createStatement().execute(sqlDelete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
