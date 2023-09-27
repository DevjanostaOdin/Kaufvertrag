package Kaufvertrag.dataLayer.services;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.exceptions.DaoException;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class WareService {

    private final Scanner scanner;
    private final IDao<IWare, Long> wareDao;

    public WareService(Scanner scanner, IDao<IWare, Long> wareDao) {
        this.scanner = scanner;
        this.wareDao = wareDao;
    }

    public void wareOptionen() {
        boolean weiterImUntermenue = true;
        while (weiterImUntermenue) {
            System.out.println("Bitte wählen Sie eine Option:");
            System.out.println("1. Ware hinzufügen");
            System.out.println("2. Ware anzeigen");
            System.out.println("3. Ware bearbeiten");
            System.out.println("4. Ware löschen");
            System.out.println("5. Alle Waren anzeigen");
            System.out.println("6. Ins Hauptmenü zurück");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addWare();
                case 2 -> displayWare();
                case 3 -> updateWare();
                case 4 -> deleteWare();
                case 5 -> displayAllWaren();
                case 6 -> weiterImUntermenue = false;
                default -> System.out.println("Ungültige Auswahl. Bitte erneut versuchen.");
            }
        }
    }


    private void addWare() {
        System.out.print("Geben Sie die Bezeichnung der Ware ein:");
        String bezeichnung = scanner.nextLine();

        System.out.print("Geben Sie die Beschreibung der Ware ein:");
        String beschreibung = scanner.nextLine();

        double preis = 0;
        while (true) {
            System.out.println("Geben Sie den Preis der Ware ein:");
            try {
                preis = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Ungültige Eingabe. Bitte geben Sie nur Zahlen und ein Komma zur Trennung ein.");
                scanner.next();
            }
        }
        // wird benötigt, damit bei der Abfrage nach Mängeln der Zeilenumbruch nicht als Eingabe gewertet wird
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
            System.out.println("Ware erfolgreich hinzugefügt mit der ID:" + ware.getId() + ".");
        } catch (DaoException e) {
            System.out.println("Fehler beim Hinzufügen der Ware: " + e.getMessage());
        }
    }

    private void displayWare() {
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

    private void updateWare() {
        System.out.println("Geben Sie die ID der zu aktualisierenden Ware ein:");
        long id = scanner.nextLong();
        scanner.nextLine();

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

                System.out.println("Aktuelle Mängel: " + String.join(", ", ware.getMaengel()));
                System.out.print("Geben Sie die neuen Mängel der Ware ein (durch Komma getrennt, leer lassen für keine Änderung):");
                String maengelInput = scanner.nextLine();
                if (!maengelInput.isEmpty()) {
                    List<String> maengel = Arrays.asList(maengelInput.split(","));
                    ware.setMaengel(maengel);
                }

                System.out.println("Aktuelle Besonderheiten: " + String.join(", ", ware.getBesonderheiten()));
                System.out.print("Geben Sie die neuen Besonderheiten der Ware ein (durch Komma getrennt, leer lassen für keine Änderung):");
                String besonderheitenInput = scanner.nextLine();
                if (!besonderheitenInput.isEmpty()) {
                    List<String> besonderheiten = Arrays.asList(besonderheitenInput.split(","));
                    ware.setBesonderheiten(besonderheiten);
                }

                wareDao.update(ware);
                System.out.println("Ware erfolgreich aktualisiert.");
            } else {
                System.out.println("Ware mit ID " + id + " wurde nicht gefunden.");
            }
        } catch (DaoException e) {
            System.out.println("Fehler beim Aktualisieren der Ware: " + e.getMessage());
        }
    }

    private void deleteWare() {
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

    private void displayAllWaren() {
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
