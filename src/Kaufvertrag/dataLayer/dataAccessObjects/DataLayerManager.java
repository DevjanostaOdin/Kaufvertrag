package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.XML.DataLayerXml;
import Kaufvertrag.dataLayer.dataAccessObjects.sqlite.DataLayerSqlite;
import com.sun.source.tree.IfTree;
import jdk.jshell.spi.ExecutionControl;
import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;

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

    public IDataLayer getDataLayer() { // TO COMPLETE !!
        if (persistenceType.equalsIgnoreCase("sqlite")) {
            return new DataLayerSqlite();
        }
        return null;
    }

    private String readPersistenceType() {

        return persistenceType;
    }
}
