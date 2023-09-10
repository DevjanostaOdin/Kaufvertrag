package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;

public class DataLayerSqlite implements IDataLayer {
    ConnectionManager connectionManager = new ConnectionManager();

    @Override
    public IDao<IVertragspartner, String> getDaoVertragspartner() {
        return new VertragspartnerDaoSqlite();
    }

    @Override
    public IDao<IWare, Long> getDaoWare() {
        return new WareDaoSqlite(connectionManager);
    }
}