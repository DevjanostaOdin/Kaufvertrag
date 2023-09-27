package Kaufvertrag.presentatitionLayer;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.DataLayerManager;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.dataLayer.services.VertragspartnerService;
import Kaufvertrag.dataLayer.services.WareService;
import Kaufvertrag.exceptions.DaoException;


import java.util.Scanner;

public class Programm {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws DaoException {
        DataLayerManager dataLayerManager = DataLayerManager.getInstance();

        System.out.println("Möchten Sie mit Ware oder Vertragspartner arbeiten?");
        System.out.println("1. Ware");
        System.out.println("2. Vertragspartner");
        System.out.println("3. Beenden");

        int auswahl = scanner.nextInt();
        scanner.nextLine();

        switch (auswahl) {
            case 1 -> {
                IDao<IWare, Long> wareDao = dataLayerManager.getDataLayer().getDaoWare();
                WareService wareService = new WareService(scanner, wareDao);
                wareService.wareOptionen();
            }
            case 2 -> {
                IDao<IVertragspartner, String> vertragspartnerDao = dataLayerManager.getDataLayer().getDaoVertragspartner();
                VertragspartnerService vertragspartnerService = new VertragspartnerService(scanner, vertragspartnerDao);
                vertragspartnerService.vertragspartnerOptionen();
            }
            case 3 -> System.out.println("Programm wird beendet.");
            default -> System.out.println("Ungültige Auswahl. Bitte erneut versuchen.");
        }
    }
}