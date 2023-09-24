package Kaufvertrag.dataLayer.services;

import Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.exceptions.DaoException;

import java.util.List;
import java.util.Scanner;

public class VertragspartnerService {
    private final Scanner scanner;
    private final IDao<IVertragspartner, String> vertragspartnerDao;

    public VertragspartnerService(Scanner scanner, IDao<IVertragspartner, String> vertragspartnerDao) {
        this.scanner = scanner;
        this.vertragspartnerDao = vertragspartnerDao;
    }

    public void vertragspartnerOptionen() {
        while (true) {
            System.out.println("Bitte wählen Sie eine Option:");
            System.out.println("1. Vertragspartner hinzufügen");
            System.out.println("2. Vertragspartner nach Ausweisnummer auslesen");
            System.out.println("3. Vertragspartner bearbeiten");
            System.out.println("4. Vertragspartner nach Ausweisnummer löschen");
            System.out.println("5. Alle Vertragspartner auslesen");
            System.out.println("6. Programm beenden");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addVertragspartner();
                case 2 -> displayVertragspartner();
                case 3 -> updateVertragspartner();
                case 4 -> deleteVertragspartner();
                case 5 -> displayAllVertragspartner();
                case 6 -> {
                    return;
                }
                default -> System.out.println("Ungültige Auswahl. Bitte erneut versuchen.");
            }
        }
    }

    private void addVertragspartner() {
        IVertragspartner vertragspartner = new Vertragspartner();
        IAdresse adresse = new Adresse();

        System.out.println("Geben Sie die Ausweisnummer ein:");
        String ausweisnummer = scanner.next();
        scanner.nextLine();
        System.out.print("Geben Sie den Vornamen ein:");
        String vorname = scanner.nextLine();
        System.out.print("Geben Sie den Nachnamen ein:");
        String nachname = scanner.nextLine();
        System.out.print("Geben Sie die Strasse ein:");
        String strasse = scanner.nextLine();
        System.out.print("Geben Sie die Hausnummer ein:");
        String hausnr = scanner.nextLine();
        System.out.print("Geben Sie die PLZ ein:");
        String plz = scanner.nextLine();
        System.out.print("Geben Sie den Ort ein:");
        String ort = scanner.nextLine();

        vertragspartner.setAusweisNr(ausweisnummer);
        vertragspartner.setVorname(vorname);
        vertragspartner.setNachname(nachname);
        adresse.setStrasse(strasse);
        adresse.setHausNr(hausnr);
        adresse.setPlz(plz);
        adresse.setOrt(ort);
        vertragspartner.setAdresse(adresse);

        try {
            vertragspartnerDao.create(vertragspartner);
        } catch (DaoException e) {
            System.out.println(e.getMessage());
        }
    }

    private void displayVertragspartner() {
        System.out.println("Geben Sie die Ausweisnummer des Vertragpartners ein:");
        String id = scanner.next();
        scanner.nextLine();

        try {
            IVertragspartner vertragspartner = vertragspartnerDao.read(id);
            if (vertragspartner != null) {
                System.out.println(vertragspartner);
            } else {
                System.out.println("Vertragspartner mit dem Ausweisnummer " + id + " wurde nicht gefunden.");
            }
        } catch (DaoException e) {
            System.out.println("Fehler beim Abrufen des Vertragspartners: " + e.getMessage());
        }
    }

    private void updateVertragspartner() {
        System.out.println("Geben Sie die Ausweisnummer des zu aktualisierenden Vertragpartners ein:");
        String ausweisnummer = scanner.next();
        scanner.nextLine();
        IVertragspartner vertragspartner;
        IAdresse adresse;

        try {
            vertragspartner = vertragspartnerDao.read(ausweisnummer);
            adresse = vertragspartner.getAdresse();
            try {

                System.out.println("Geben Sie den neuen Vornamen des Vertragspartners ein (aktuell: " + vertragspartner.getVorname() + "):");
                String vorname = scanner.nextLine();
                vertragspartner.setVorname(vorname);

                System.out.println("Geben Sie den neuen Nachnamen des Vertragspartners ein (aktuell: " + vertragspartner.getNachname() + "):");
                String nachname = scanner.nextLine();
                vertragspartner.setNachname(nachname);

                System.out.println("Geben Sie die neue Straße ein (aktuell: " + adresse.getStrasse() + "):");
                String strasse = scanner.nextLine();
                adresse.setStrasse(strasse);

                System.out.println("Geben Sie die neue Hausnummer ein (aktuell: " + adresse.getHausNr() + "):");
                String hausnr = scanner.nextLine();
                adresse.setHausNr(hausnr);

                System.out.println("Geben Sie die neue Postleitzahl ein (aktuell: " + adresse.getPlz() + "):");
                String plz = scanner.nextLine();
                adresse.setPlz(plz);

                System.out.println("Geben Sie den neuen Ort ein (aktuell: " + adresse.getOrt() + "):");
                String ort = scanner.nextLine();
                adresse.setOrt(ort);

                vertragspartner.setAdresse(adresse);

                vertragspartnerDao.update(vertragspartner);


            } catch (DaoException e) {
                System.out.println("Fehler beim Bearbeiten des Vertragspartners " + e.getMessage());
            }
        } catch (DaoException e) {
            System.out.println("Vertragspartner mit Ausweisnummer " + ausweisnummer + " wurde nicht gefunden." + e.getMessage());
        }
    }

    private void deleteVertragspartner() {
        System.out.println("Geben Sie die Ausweisnummer des Vertragpartners ein:");
        String id = scanner.next();
        scanner.nextLine();

        try {
            vertragspartnerDao.delete(id);
        } catch (DaoException e) {
            System.out.println("Fehler beim Löschen des Vertragspartners" + e.getMessage());
        }
    }

    private void displayAllVertragspartner() {
        try {
            List<IVertragspartner> listVertragspartner = vertragspartnerDao.readAll();
            if (listVertragspartner != null) {
                for (IVertragspartner vertragspartner : listVertragspartner) {
                    System.out.println(vertragspartner);
                }
            }
        } catch (DaoException e) {
            System.out.println("Fehler beim Abrufen des Vertragspartners: " + e.getMessage());
        }
    }
}

