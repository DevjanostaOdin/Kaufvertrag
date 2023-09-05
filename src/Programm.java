import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.dataLayer.dataAccessObjects.DataLayerManager;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;
import Kaufvertrag.dataLayer.dataAccessObjects.XML.DataLayerXml;
import Kaufvertrag.dataLayer.dataAccessObjects.sqlite.ConnectionManager;
import Kaufvertrag.dataLayer.dataAccessObjects.sqlite.DataLayerSqlite;
import Kaufvertrag.dataLayer.dataAccessObjects.sqlite.VertragspartnerDaoSqlite;
import Kaufvertrag.dataLayer.dataAccessObjects.sqlite.WareDaoSqlite;
import Kaufvertrag.exceptions.DaoException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Programm {
    private static final Scanner scanner = new Scanner(System.in);
    IDao<IVertragspartner, String> vertragspartnerDao = DataLayerManager.getInstance().getDataLayer().getDaoVertragspartner();
    private static IDao<IWare, Long> wareDao;



    public static void main(String[] args) {
    /*
        IVertragspartner vertPart = new Vertragspartner("100", "Dollar");
        vertPart.setAusweisNr("0111111");
        IAdresse adresse = new Adresse("Danzigerstr", "10", "66666", "Hamburg");
        vertPart.setAdresse(adresse);

        IDao<IVertragspartner, String> dao = DataLayerManager.getInstance().getDataLayer().getDaoVertragspartner();

        //dao.create(vertPart);
        //dao.update(vertPart);
        dao.read("8");
        //dao.delete("VERTRAGSPARTNER");
        //dao.delete("ADRESSE");
    }*/


        /* Ablauf des Verbindungsaufbaus
         * 1. Eine Instanz des DataLayerManagers holen mit .getInstance
         * 2. Wenn keine instance vorhanden ist, wird ein new DataLayerManager erstellt
         * 3. Es wird ausgewählt, welcher PersistenceType gebraucht wird (also SQL oder XML)
         * 4. Daraufhin wird ein DataLayer mit entweder SQL oder XML angelegt
         * 5. Dann wird je nachdem, ob ein Vertragspartner oder eine Ware angelegt werden soll, das entsprechende DAO
         * aufgerufen mit der .getDao... Methode
         * 6. Im nöchsten Schritt wird eine neue instanz von Ware oder Vertragspartner erstellt.
         * 7. Anschließend kann das neu erstellte Projekt übergeben werden an wareDao oder vertragspartnerDao und dort der
         * Datenbank hinzugefügt werden per .create Methode.
         * */

        IDataLayer dataLayer = DataLayerManager.getInstance().getDataLayer();

            System.out.println("Möchten Sie mit Ware oder Vertragspartner arbeiten?");
            System.out.println("1. Ware");
            System.out.println("2. Vertragspartner");
            System.out.println("3. Beenden");

            int auswahl = scanner.nextInt();
            scanner.nextLine();

        switch (auswahl) {
            case 1 -> wareOptionen();
            case 2 -> {
                // vertragspartnerOptionen();
            }
            case 3 -> {
                System.out.println("Programm wird beendet.");
            }
            default -> System.out.println("Ungültige Auswahl. Bitte erneut versuchen.");
        }
    }

    private static void wareOptionen() {
        while (true) {
            System.out.println("Bitte wählen Sie eine Option:");
            System.out.println("1. Ware hinzufügen");
            System.out.println("2. Ware anzeigen");
            System.out.println("3. Ware bearbeiten");
            System.out.println("4. Ware löschen");
            System.out.println("5. Alle Waren anzeigen");
            System.out.println("6. Zurück zum Hauptmenü");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addWare();
                case 2 -> displayWare();
                case 3 -> updateWare();
                case 4 -> deleteWare();
                case 5 -> displayAllWaren();
                case 6 -> {
                    return; // Zurück zum Hauptmenü
                }
                default -> System.out.println("Ungültige Auswahl. Bitte erneut versuchen.");
            }
        }
    }

    // alle Optionen für Ware
    private static void addWare() {
        System.out.print("Geben Sie die Bezeichnung der Ware ein:");
        String bezeichnung = scanner.nextLine();

        System.out.print("Geben Sie die Beschreibung der Ware ein:");
        String beschreibung = scanner.nextLine();

        System.out.println("Geben Sie den Preis der Ware ein:");
        double preis = scanner.nextDouble();
        //wird benötigt, da ansonsten die nächste eingabe als /n gewertet wird
        scanner.nextLine();

        System.out.println("Geben Sie die Mängel der Ware ein (durch Komma getrennt):");
        String maengelInput = scanner.nextLine();
        List<String> maengel = Arrays.asList(maengelInput.split(","));

        System.out.println("Geben Sie die Besonderheiten der Ware ein (durch Komma getrennt):");
        String besonderheitenInput = scanner.nextLine();
        List<String> besonderheiten = Arrays.asList(besonderheitenInput.split(","));

        Ware ware = new Ware(bezeichnung, preis);
        ware.setBeschreibung(beschreibung);
        ware.setMaengel(maengel);
        ware.setBesonderheiten(besonderheiten);

        try {
            wareDao.create(ware);
            System.out.println("Ware erfolgreich hinzugefügt.");
        } catch (DaoException e) {
            System.out.println("Fehler beim Hinzufügen der Ware: " + e.getMessage());
        }

    }


    private static void displayWare() {
        System.out.println("Geben Sie die ID der Ware ein:");
        long id = scanner.nextLong();
        scanner.nextLine();

        try {
            IWare ware = wareDao.read(id);
            if (ware != null) {
                System.out.println(ware);
            } else {
                System.out.println("Ware mit ID " + id + " wurde nicht gefunden.");
            }
        } catch (DaoException e) {
            System.out.println("Fehler beim Abrufen der Ware: " + e.getMessage());
        }
    }

    private static void updateWare() {
        System.out.println("Geben Sie die ID der zu aktualisierenden Ware ein:");
        long id = scanner.nextLong();
        scanner.nextLine(); // Zeilenumbruchzeichen (\n) bleibt sonst im Eingabefeld

        try {
            IWare ware = wareDao.read(id);
            if (ware != null) {
                System.out.print("Geben Sie die neue Bezeichnung der Ware ein (aktuell: " + ware.getBezeichnung() + "):");
                String bezeichnung = scanner.nextLine();
                ware.setBezeichnung(bezeichnung);

                System.out.print("Geben Sie die neue Beschreibung der Ware ein (aktuell: " + ware.getBeschreibung() + "):");
                String beschreibung = scanner.nextLine();
                ware.setBeschreibung(beschreibung);

                System.out.println("Geben Sie den neuen Preis der Ware ein (aktuell: " + ware.getPreis() + "):");
                double preis = scanner.nextDouble();
                ware.setPreis(preis);
                scanner.nextLine();

                wareDao.update(ware);
                System.out.println("Ware erfolgreich aktualisiert.");
            } else {
                System.out.println("Ware mit ID " + id + " wurde nicht gefunden.");
            }
        } catch (DaoException e) {
            System.out.println("Fehler beim Aktualisieren der Ware: " + e.getMessage());
        }
    }

    private static void deleteWare() {
        System.out.println("Geben Sie die ID der zu löschenden Ware ein:");
        long id = scanner.nextLong();
        scanner.nextLine();

        try {
            wareDao.delete(id);
            System.out.println("Ware erfolgreich gelöscht.");
        } catch (DaoException e) {
            System.out.println("Fehler beim Löschen der Ware: " + e.getMessage());
        }
    }


    private static void displayAllWaren() {
        try {
            List<IWare> warenListe = wareDao.readAll();

            if (warenListe.isEmpty()) {
                System.out.println("Es sind keine Waren vorhanden.");
                return;
            }

            for (IWare ware : warenListe) {
                System.out.println(ware);
            }

        } catch (DaoException e) {
            System.out.println("Fehler beim Abrufen der Waren: " + e.getMessage());
        }
    }
}

