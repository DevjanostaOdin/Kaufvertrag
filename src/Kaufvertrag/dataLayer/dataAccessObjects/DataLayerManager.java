package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.XML.DataLayerXml;
import Kaufvertrag.dataLayer.dataAccessObjects.sqlite.DataLayerSqlite;
import com.sun.source.tree.IfTree;
import jdk.jshell.spi.ExecutionControl;
import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;

import java.util.Scanner;

public class DataLayerManager {
    private static DataLayerManager instance;
    private String persistenceType;

    public static DataLayerManager getInstance() {
        if (instance == null) {
            instance = new DataLayerManager();
        }
        return instance;
    }

    private DataLayerManager() {

    }

    public IDataLayer getDataLayer() {
        if (readPersistenceType().equalsIgnoreCase("sqlite")) {
            return new DataLayerSqlite();
        } else if (readPersistenceType().equalsIgnoreCase("xml")){
            return new DataLayerXml();
        }
        return null;
    }

    private String readPersistenceType() {
        // WAS IST DIE IDEE DAHINTER
        Scanner sc = new Scanner(System.in);
        System.out.println("Give persistence type: (sqlite or xml)");
        String type = String.valueOf(sc.nextLine());
        persistenceType = type;
        return persistenceType;
    }
}
