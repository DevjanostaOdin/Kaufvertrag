package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
                                                // WIR HATTEN IDAO OHNE <..., ...> DAHER HATTEN WIR OBJECT TRALALA
public class VertragspartnerDaoSqlite implements IDao<IVertragspartner, String> {


    @Override // FRAGEN WAS WAR DIE IDEE DAHINTER
    public IVertragspartner create() {
        return new Vertragspartner();
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
        Connection connection = getConnection();
        IVertragspartner vertragspartner = new Vertragspartner();
        IAdresse adresse = new Adresse();

        String sqlReadPerson = "SELECT * FROM VERTRAGSPARTNER WHERE ID = " + id;
        String sqlReadAdresse = "SELECT * FROM ADRESSE WHERE ID = " + id;
        System.out.println(sqlReadPerson);
        System.out.println(sqlReadAdresse);
        try{
            ResultSet resultSetPerson = connection.createStatement().executeQuery(sqlReadPerson);
            ResultSet resultSetAdresse = connection.createStatement().executeQuery(sqlReadAdresse);
            if(resultSetAdresse.next()){
                adresse.setStrasse(resultSetAdresse.getString("STRASSE"));
                adresse.setHausNr(resultSetAdresse.getString("HAUSNUMMER"));
                adresse.setPlz(resultSetAdresse.getString("PLZ"));
                adresse.setOrt(resultSetAdresse.getString("ORT"));
            }
            if(resultSetPerson.next()){
                vertragspartner.setAusweisNr(resultSetPerson.getString("AUSWEIS_NR"));
                vertragspartner.setVorname(resultSetPerson.getString("VORNAME"));
                vertragspartner.setNachname(resultSetPerson.getString("NACHNAME"));
                vertragspartner.setAdresse(adresse);

            }
        } catch (SQLException e){
            e.printStackTrace();
        }


         return vertragspartner;
    }

    @Override
    public List<IVertragspartner> readAll() {
        Connection connection = getConnection();
        List<IVertragspartner> listVertragspartner = new ArrayList<IVertragspartner>();

        String sqlMaxPK = "SELECT * FROM VERTRAGSPARTNER;";
        System.out.println(sqlMaxPK);
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(sqlMaxPK);
            int id = 0;
            while (resultSet.next()){
                id++;
                listVertragspartner.add(read(Integer.toString(id)));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        for(IVertragspartner value : listVertragspartner){
            System.out.println(value.toString());
        }
        return listVertragspartner;
    }





    @Override
    public void update(IVertragspartner objectTpUpdate) {

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
            e.printStackTrace();
        }
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
