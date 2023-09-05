package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.dataLayer.dataAccessObjects.XML.DataLayerXml;
import Kaufvertrag.dataLayer.dataAccessObjects.sqlite.DataLayerSqlite;
import Kaufvertrag.exceptions.DaoException;


import java.util.Scanner;

public class DataLayerManager {
    private static DataLayerManager instance;

    private DataLayerManager() {}

    public static DataLayerManager getInstance() {
        if (instance == null) {
            instance = new DataLayerManager();
        }
        return instance;
    }

    public IDataLayer getDataLayer() {
        String type;
        do {
            type = readPersistenceType();
            if (type.equalsIgnoreCase("sqlite")) {
                return new DataLayerSqlite();
            } else if (type.equalsIgnoreCase("xml")) {
                return new DataLayerXml();
            } else {
                System.out.println("Ungültige Eingabe. Bitte geben Sie 'sqlite' oder 'xml' ein.");
            }
        } while (!type.equalsIgnoreCase("sqlite") && !type.equalsIgnoreCase("xml"));
        // return null wird nie erreicht, aber return wird benötigt für den Compiler
        return null;
    }

    private String readPersistenceType() {
        String persistenceType;
        Scanner sc = new Scanner(System.in);
        System.out.println("Give persistence type: (sqlite or xml)");
        persistenceType = String.valueOf(sc.nextLine());
        return persistenceType;
    }
}
