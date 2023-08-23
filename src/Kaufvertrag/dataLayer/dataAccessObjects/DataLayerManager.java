package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.XML.DataLayerXml;
import Kaufvertrag.dataLayer.dataAccessObjects.sqlite.DataLayerSqlite;
import Kaufvertrag.exceptions.DaoException;
import com.sun.source.tree.IfTree;
import jdk.jshell.spi.ExecutionControl;

public class DataLayerManager {
    private static DataLayerManager instance;
    private String persistenceType;

    private DataLayerManager() {
        if (instance == null) {
            instance = new DataLayerManager();
        }
    }

    public static DataLayerManager getInstance() {
        return instance;
    }

    public IDataLayer getDataLayer() throws DaoException {
        if (readPersistenceType().equalsIgnoreCase("XML")) {
            IDataLayer dataLayer = new DataLayerXml();
            return dataLayer;
        }
        else if (readPersistenceType().equalsIgnoreCase("Sqlite")) {
            IDataLayer dataLayer = new DataLayerSqlite();
            return dataLayer;
        }
        else throw new DaoException("Wrong data layer.");
    }

    private String readPersistenceType() {
        return persistenceType;
    }
}
