package Kaufvertrag.dataLayer.dataAccessObjects;

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

    // TODO:
//    public IDataLayer getDataLayer() throws DaoException {
//        return
//    }

    private String readPersistenceType() {
        return persistenceType;
    }
}
