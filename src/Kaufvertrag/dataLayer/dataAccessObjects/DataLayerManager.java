package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.XML.DataLayerXml;
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

//    public IDataLayer getDataLayer() {
//        if (instanceOf)
//        IDataLayer dataLayer = new DataLayerXml();
//        return dataLayer;
//    }

    private String readPersistenceType() {
        return persistenceType;
    }
}
