package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WareDaoSqlite implements IDao<IWare, Long> {

    private final ConnectionManager connectionManager;

    public WareDaoSqlite(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public IWare create() {
        return new Ware();
    }

    @Override
    public void create(IWare ware) throws DaoException {
        String sql = "INSERT INTO WARE (bezeichnung, beschreibung, preis, maengel, besonderheiten) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = connectionManager.getNewConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, ware.getBezeichnung());
            preparedStatement.setString(2, ware.getBeschreibung());
            preparedStatement.setDouble(3, ware.getPreis());
            preparedStatement.setString(4, String.join(",", ware.getMaengel()));
            preparedStatement.setString(5, String.join(",", ware.getBesonderheiten("")));
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    long generatedId = resultSet.getLong(1);
                    ((Ware) ware).setId(generatedId);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Erstellen der Ware in der Datenbank.");
        }
    }



    @Override
    public IWare read(Long id) throws DaoException {
        String sql = "SELECT * FROM WARE WHERE id = ?";
        try (Connection connection = connectionManager.getNewConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSetToWare(resultSet);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Lesen der Ware in der Datenbank.");
        }
    }

    @Override
    public List<IWare> readAll() throws DaoException {
        List<IWare> waren = new ArrayList<>();
        String sql = "SELECT * FROM WARE";
        try (Connection connection = connectionManager.getNewConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                waren.add(resultSetToWare(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Lesen der Waren in der Datenbank.");
        }
        return waren;
    }

    @Override
    public void update(IWare ware) throws DaoException {
        String sql = "UPDATE WARE SET bezeichnung = ?, beschreibung = ?, preis = ?, maengel = ?, besonderheiten = ? WHERE id = ?";
        try (Connection connection = connectionManager.getNewConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, ware.getBezeichnung());
            preparedStatement.setString(2, ware.getBeschreibung());
            preparedStatement.setDouble(3, ware.getPreis());
            preparedStatement.setString(4, String.join(",", ware.getMaengel()));
            preparedStatement.setString(5, String.join(",", ware.getBesonderheiten("")));
            preparedStatement.setLong(6, ware.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Updaten der Ware in der Datenbank.");
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM WARE WHERE id = ?";
        try (Connection connection = connectionManager.getNewConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Fehler beim Löschen der Ware in der Datenbank.");
        }
    }

    private IWare resultSetToWare(ResultSet resultSet) throws SQLException {
        Ware ware = new Ware(resultSet.getString("bezeichnung"), resultSet.getDouble("preis"));
        ware.setId(resultSet.getLong("id"));
        ware.setBeschreibung(resultSet.getString("beschreibung"));
        ware.setMaengel(Arrays.asList(resultSet.getString("maengel").split(",")));
        ware.setBesonderheiten(Arrays.asList(resultSet.getString("besonderheiten").split(",")));
        return ware;
    }

}
