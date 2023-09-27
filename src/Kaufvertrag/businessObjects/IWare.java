package Kaufvertrag.businessObjects;

import java.util.List;

/**
 * Interface für die Darstellung einer Ware im System.
 */
public interface IWare {

    /**
     * Function name: getId
     *
     * @return (long) Die ID der Ware.
     *
     * Inside the function: Gibt die eindeutige ID der Ware zurück.
     */
    long getId();

    /**
     * Function name: getBezeichnung
     *
     * @return (String) Die Bezeichnung der Ware.
     *
     * Inside the function: Gibt die Bezeichnung der Ware zurück.
     */
    String getBezeichnung();

    /**
     * Function name: setBezeichnung
     *
     * @param bezeichnung (Die neue Bezeichnung für die Ware.)
     *
     * Inside the function: Setzt eine neue Bezeichnung für die Ware.
     */
    void setBezeichnung(String bezeichnung);

    /**
     * Function name: getBeschreibung
     *
     * @return (String) Die Beschreibung der Ware.
     *
     * Inside the function: Gibt die Beschreibung der Ware zurück.
     */
    String getBeschreibung();

    /**
     * Function name: setBeschreibung
     *
     * @param beschreibung (Die neue Beschreibung für die Ware.)
     *
     * Inside the function: Setzt eine neue Beschreibung für die Ware.
     */
    void setBeschreibung(String beschreibung);

    /**
     * Function name: getPreis
     *
     * @return (double) Der Preis der Ware.
     *
     * Inside the function: Gibt den Preis der Ware zurück.
     */
    double getPreis();

    /**
     * Function name: setPreis
     *
     * @param preis (Der neue Preis für die Ware.)
     *
     * Inside the function: Setzt einen neuen Preis für die Ware.
     */
    void setPreis(double preis);

    /**
     * Function name: getBesonderheiten
     *
     * @return List<String> (Eine Liste von Besonderheiten der Ware.)
     *
     * Inside the function: Gibt eine Liste von Besonderheiten der Ware zurück.
     */
    List<String> getBesonderheiten();

    /**
     * Function name: setBesonderheiten
     *
     * @param besonderheiten (Eine Liste von neuen Besonderheiten für die Ware.)
     *
     * Inside the function: Setzt eine neue Liste von Besonderheiten für die Ware.
     */
    void setBesonderheiten(List<String> besonderheiten);

    /**
     * Function name: getMaengel
     *
     * @return List<String> (Eine Liste von Mängeln der Ware.)
     *
     * Inside the function: Gibt eine Liste von Mängeln der Ware zurück.
     */
    List<String> getMaengel();

    /**
     * Function name: setMaengel
     *
     * @param maengel (Eine Liste von neuen Mängeln für die Ware.)
     *
     * Inside the function: Setzt eine neue Liste von Mängeln für die Ware.
     */
    void setMaengel(List<String> maengel);
}
