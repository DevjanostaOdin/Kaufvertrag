package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.exceptions.DaoException;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
                                                // WIR HATTEN IDAO OHNE <..., ...> DAHER HATTEN WIR OBJECT TRALALA
public class VertragspartnerDaoSqlite implements IDao<IVertragspartner, String> {


    @Override // FRAGEN WAS WAR DIE IDEE DAHINTER
    public IVertragspartner create() {
        return new Vertragspartner();
    }

    @Override   // HIER HATTEN WIR NICHT IVERTRAGSPARTNER SONDERN OBJECT ...
    public void create(IVertragspartner objectToInsert) throws DaoException {

        Connection connection = getConnection();
        String sqlTemplatePerson = "INSERT INTO" +
                " VERTRAGSPARTNER (AUSWEIS_NR, VORNAME, NACHNAME) " +
                " VALUES(\"%s\", \"%s\", \"%s\")";

        String sqlTemplateAdresse = "INSERT INTO" +
                " ADRESSE (STRASSE, HAUSNUMMER, PLZ, ORT) " +
                " VALUES(\"%s\",\"%s\", \"%s\", \"%s\")";



        String sqlPerson = String.format(sqlTemplatePerson,
                objectToInsert.getAusweisNr(),
                objectToInsert.getVorname(),
                objectToInsert.getNachname());

        String sqlAdresse = String.format(sqlTemplateAdresse,
                objectToInsert.getAdresse().getStrasse(),
                objectToInsert.getAdresse().getHausNr(),
                objectToInsert.getAdresse().getPlz(),
                objectToInsert.getAdresse().getOrt());

        System.out.println(sqlPerson);
        System.out.println(sqlAdresse);




        // Exception klasse nutzen!
        try {
            connection.createStatement().executeUpdate(sqlPerson);
            connection.createStatement().executeUpdate(sqlAdresse);
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Erstellen der Ware in der Datenbank.");
        }
    }

     private Connection getConnection() throws DaoException {
         ConnectionManager conMan = new ConnectionManager();
         Connection connection = conMan.getNewConnection();
         return connection;
     }

                                                    @Override
    public IVertragspartner read(String id) throws DaoException {
        Connection connection = getConnection();
        IVertragspartner vertragspartner = new Vertragspartner();
        IAdresse adresse = new Adresse();

        String sqlRead = "SELECT * FROM VERTRAGSPARTNER WHERE ID = " + id;
        String sqlReadAdresse = "SELECT * FROM ADRESSE WHERE ID = " + id;
        System.out.println(sqlRead);
        try{
            ResultSet resultSet = connection.createStatement().executeQuery(sqlRead);
            if(resultSet.next()){
                vertragspartner.setAusweisNr(resultSet.getString("AUSWEIS_NR"));
                vertragspartner.setVorname(resultSet.getString("VORNAME"));
                vertragspartner.setNachname(resultSet.getString("NACHNAME"));
//                adresse.setStrasse(resultSet.getString("STRASSE"));
//                adresse.setHausNr(resultSet.getString("HAUSNUMMER"));
//                adresse.setPlz(resultSet.getString("PLZ"));
//                adresse.setOrt(resultSet.getString("ORT"));
                vertragspartner.setAdresse(adresse);
                return vertragspartner;
            }
        } catch (SQLException e){
            throw new DaoException("Fehler beim Lesen der Ware in der Datenbank.");
        }

    return null;
    }

    @Override
    public List<IVertragspartner> readAll() {
        List<IVertragspartner> listVertragspartner = new ArrayList<IVertragspartner>();

        IVertragspartner vertragspartner = new Vertragspartner();
        vertragspartner.getAusweisNr();

        String sqlReadAll = "SELECT * " +
                " FROM VERTRAGSPARTNER ";
        System.out.println(sqlReadAll);

        return listVertragspartner;
    }

    @Override
    public void update(IVertragspartner objectTpUpdate) throws DaoException  {

        Connection connection = getConnection();
        String sqlUpdateAdresse = "UPDATE " +
                " ADRESSE " +
                " SET STRASSE = \'" + objectTpUpdate.getAdresse().getStrasse() + "\'," +
                " HAUSNUMMER = \'" + objectTpUpdate.getAdresse().getHausNr() + "\', " +
                " PLZ = \'" + objectTpUpdate.getAdresse().getPlz() + "\', " +
                " ORT = \'" + objectTpUpdate.getAdresse().getOrt() + "\'" +
                " WHERE ID = 2" +
                " ;" ;

        String sqlUpdatePerson = "UPDATE " +
                " VERTRAGSPARTNER " +
                " SET AUSWEIS_NR = \'" + objectTpUpdate.getAusweisNr() + "\'," +
                " VORNAME = \'" + objectTpUpdate.getVorname() + "\'," +
                " NACHNAME = \'" + objectTpUpdate.getNachname()+ "\'" +
                " WHERE ID = 8;";

        System.out.println(sqlUpdateAdresse);
        System.out.println(sqlUpdatePerson);
        try {
            connection.createStatement().execute(sqlUpdateAdresse);
            connection.createStatement().execute(sqlUpdatePerson);
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Updaten der Ware in der Datenbank.");
        }
    }

    @Override
    public void delete(String id) throws DaoException {
        Connection connection = getConnection();
        String sqlDelete = "DELETE " +
                "FROM " + id;
        System.out.println(sqlDelete);
        try {
            connection.createStatement().execute(sqlDelete);
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Löschen der Ware in der Datenbank.");
        }
    }
}
