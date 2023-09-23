package Kaufvertrag.dataLayer.dataAccessObjects.sqlite;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;
import Kaufvertrag.exceptions.DaoException;

public class DataLayerSqlite implements IDataLayer {

    @Override
    public IDao<IVertragspartner, String> getDaoVertragspartner() throws DaoException {
        return new VertragspartnerDaoSqlite();
    }

    @Override
    public IDao<IWare, Long> getDaoWare() throws DaoException {
        return new WareDaoSqlite();
    }
}