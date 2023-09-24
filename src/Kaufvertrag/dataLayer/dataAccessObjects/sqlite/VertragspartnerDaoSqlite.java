package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.exceptions.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VertragspartnerDaoSqlite implements IDao<IVertragspartner, String> {

    ConnectionManager connectionManager;

    public VertragspartnerDaoSqlite() throws DaoException {
        connectionManager = new ConnectionManager();
    }

    @Override
    public IVertragspartner create() {
        return new Vertragspartner();
    }

    @Override
    public void create(IVertragspartner objectToInsert) throws DaoException {

        Connection connection = getConnection();

        String sqlTemplateAdresse = "PRAGMA foreign_keys = ON; INSERT INTO" +
                " ADRESSE (STRASSE, HAUSNUMMER, PLZ, ORT) " +
                " VALUES(\"%s\",\"%s\", \"%s\", \"%s\")";

        String sqlAdresse = String.format(sqlTemplateAdresse,
                objectToInsert.getAdresse().getStrasse(),
                objectToInsert.getAdresse().getHausNr(),
                objectToInsert.getAdresse().getPlz(),
                objectToInsert.getAdresse().getOrt());

        try {
            connection.createStatement().executeUpdate(sqlAdresse);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sqlAdressId = "SELECT Id FROM ADRESSE WHERE STRASSE = \'" + objectToInsert.getAdresse().getStrasse() + "\'" +
                " AND HAUSNUMMER = \'" + objectToInsert.getAdresse().getHausNr() + "\'" +
                " AND PLZ = \'" + objectToInsert.getAdresse().getPlz() + "\'" +
                " AND ORT = \'" + objectToInsert.getAdresse().getOrt() + "\';";

        int adressId;
        String sqlPerson;

        String sqlTemplatePerson = "PRAGMA foreign_keys = ON; INSERT INTO" +
                " VERTRAGSPARTNER (AUSWEIS_NR, VORNAME, NACHNAME, ADRESSE_ID) " +
                " VALUES(\"%s\", \"%s\", \"%s\", \"%s\")";

        try {
            ResultSet resultSetAdressId = connection.createStatement().executeQuery(sqlAdressId);
            System.out.println(resultSetAdressId.getInt("Id"));
            adressId = resultSetAdressId.getInt("Id");

            sqlPerson = String.format(sqlTemplatePerson,
                    objectToInsert.getAusweisNr(),
                    objectToInsert.getVorname(),
                    objectToInsert.getNachname(),
                    adressId);

            connection.createStatement().executeUpdate(sqlPerson);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if   (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Connection getConnection() throws DaoException {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getNewConnection();
        return connection;
    }

    @Override
    public IVertragspartner read(String id) throws DaoException {
        Connection connection = getConnection();
        IVertragspartner vertragspartner = new Vertragspartner();
        IAdresse adresse = new Adresse();

        String sqlReadPerson = "SELECT * FROM VERTRAGSPARTNER WHERE AUSWEIS_NR = \'" + id + "\'";

        String sqlReadAdresse;
        try {
            ResultSet resultSetPerson = connection.createStatement().executeQuery(sqlReadPerson);

            int adressId = resultSetPerson.getInt("ADRESSE_ID");

            sqlReadAdresse= "SELECT * FROM ADRESSE WHERE ID = " + adressId;
            ResultSet resultSetAdresse = connection.createStatement().executeQuery(sqlReadAdresse);

            if (resultSetAdresse.next()) {
                adresse.setStrasse(resultSetAdresse.getString("STRASSE"));
                adresse.setHausNr(resultSetAdresse.getString("HAUSNUMMER"));
                adresse.setPlz(resultSetAdresse.getString("PLZ"));
                adresse.setOrt(resultSetAdresse.getString("ORT"));
            }
            if (resultSetPerson.next()) {
                vertragspartner.setAusweisNr(resultSetPerson.getString("AUSWEIS_NR"));
                vertragspartner.setVorname(resultSetPerson.getString("VORNAME"));
                vertragspartner.setNachname(resultSetPerson.getString("NACHNAME"));
                vertragspartner.setAdresse(adresse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if   (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return vertragspartner;
    }

    @Override
    public List<IVertragspartner> readAll() throws DaoException {
        List<IVertragspartner> vertragspartners = new ArrayList<>();
        String sql = "SELECT TOP 1 FROM VERTRAGSPARTNER";
        //String sql = "SELECT * FROM VERTRAGSPARTNER";

        try (Connection connection = connectionManager.getNewConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                vertragspartners.add(resultSetVertragspartner(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Lesen der Vertragspartner in der Datenbank.");
        }

        return vertragspartners;
    }

    private IVertragspartner resultSetVertragspartner(ResultSet resultSet) throws SQLException {
        Vertragspartner vertragspartner = new Vertragspartner(resultSet.getString("VORNAME"), resultSet.getString("NACHNAME"));
        vertragspartner.setAusweisNr(resultSet.getString("AUSWEISNUMMER"));
        Adresse adresse = new Adresse(resultSet.getString("STRASSE"), resultSet.getString("HAUSNUMMER"), resultSet.getString("PLZ"), resultSet.getString("ORT"));
        vertragspartner.setAdresse(adresse);
        return vertragspartner;
    }

    @Override
    public void update(IVertragspartner objectToUpdate) throws DaoException {
        Connection connection = getConnection();
        String sqlReadPerson = "SELECT * FROM VERTRAGSPARTNER WHERE AUSWEIS_NR = \'" + objectToUpdate.getAusweisNr() + "\'";

        String sqlUpdatePerson = "UPDATE VERTRAGSPARTNER" +
                " SET AUSWEIS_NR = \'" + objectToUpdate.getAusweisNr() + "\'," +
                " VORNAME = \'" + objectToUpdate.getVorname() + "\'," +
                " NACHNAME = \'" + objectToUpdate.getNachname() + "\'" +
                " WHERE AUSWEIS_NR = \'" + objectToUpdate.getAusweisNr() + "\'";


        try {
            ResultSet resultSetPerson = connection.createStatement().executeQuery(sqlReadPerson);
            int adressId = resultSetPerson.getInt("ADRESSE_ID");

            String sqlUpdateAdresse = "UPDATE ADRESSE" +
                    " SET STRASSE = \'" + objectToUpdate.getAdresse().getStrasse() + "\'," +
                    " HAUSNUMMER = \'" + objectToUpdate.getAdresse().getHausNr() + "\', " +
                    " PLZ = \'" + objectToUpdate.getAdresse().getPlz() + "\', " +
                    " ORT = \'" + objectToUpdate.getAdresse().getOrt() + "\'" +
                    " WHERE ID = \'" + adressId + "\'";

            connection.createStatement().execute(sqlUpdateAdresse);
            connection.createStatement().execute(sqlUpdatePerson);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if   (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(String id) throws DaoException {
        Connection connection = getConnection();
        String sqlDeleteAdresse;

        String sqlAdressId = "SELECT ADRESSE_ID FROM VERTRAGSPARTNER WHERE AUSWEIS_NR = \'" + id + "\'";
        String sqlDeleteVertragspartner = "DELETE FROM VERTRAGSPARTNER WHERE AUSWEIS_NR = \'" + id + "\'";
        try {
            ResultSet resultSetPerson = connection.createStatement().executeQuery(sqlAdressId);
            int adressId = resultSetPerson.getInt("ADRESSE_ID");
            sqlDeleteAdresse = "DELETE FROM ADRESSE WHERE Id = " + adressId;
            connection.createStatement().execute(sqlDeleteAdresse);
            connection.createStatement().execute(sqlDeleteVertragspartner);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if   (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}