package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WareDaoSqlite implements IDao<IWare, Long> {

    private ConnectionManager connectionManager;

    public WareDaoSqlite(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public IWare create() {

        return new Ware("", 0.0);
    }

    @Override
    public void create(IWare ware) {
        if (ware instanceof Ware) {
            String sql = "INSERT INTO ware (bezeichnung, beschreibung, preis) VALUES (?, ?, ?)";
            try (Connection connection = connectionManager.getNewConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, ware.getBezeichnung());
                preparedStatement.setString(2, ware.getBeschreibung());
                preparedStatement.setDouble(3, ware.getPreis());
                preparedStatement.executeUpdate();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    long generatedId = resultSet.getLong(1);
                    //nachfragen wegen dersetId Methode
                    /*ware.setId(generatedId);*/
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public IWare read(Long id) {
        String sql = "SELECT * FROM ware WHERE id = ?";
        try (Connection connection = connectionManager.getNewConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSetToWare(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<IWare> readAll() {
        List<IWare> waren = new ArrayList<>();
        String sql = "SELECT * FROM ware";
        try (Connection connection = connectionManager.getNewConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                waren.add(resultSetToWare(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return waren;
    }

    @Override
    public void update(IWare ware) {
        if (ware instanceof Ware) {
            String sql = "UPDATE ware SET bezeichnung = ?, beschreibung = ?, preis = ? WHERE id = ?";
            try (Connection connection = connectionManager.getNewConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, ware.getBezeichnung());
                preparedStatement.setString(2, ware.getBeschreibung());
                preparedStatement.setDouble(3, ware.getPreis());
                preparedStatement.setLong(4, ware.getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM ware WHERE id = ?";
        try (Connection connection = connectionManager.getNewConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private IWare resultSetToWare(ResultSet resultSet) throws SQLException {
        Ware ware = new Ware(resultSet.getString("bezeichnung"), resultSet.getDouble("preis"));
        ware.setId(resultSet.getLong("id"));
        ware.setBeschreibung(resultSet.getString("beschreibung"));
        return ware;
    }

}
