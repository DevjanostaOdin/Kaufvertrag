package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.dataLayer.dataAccessObjects.XML.DataLayerXml;
import Kaufvertrag.dataLayer.dataAccessObjects.XML.ServiceXml;
import Kaufvertrag.dataLayer.dataAccessObjects.sqlite.DataLayerSqlite;
import java.io.File;
import java.io.IOException;
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
                ServiceXml serviceXml = new ServiceXml();
                File file = new File(serviceXml.DATEIPFAD);
                if (!file.exists()) {
                    try {
                        serviceXml.createXmlDocument();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
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
        System.out.println("Wählen Sie den Persistenztyp: \"sqlite\" oder \"xml\"");
        persistenceType = String.valueOf(sc.nextLine());
        return persistenceType;
    }
}