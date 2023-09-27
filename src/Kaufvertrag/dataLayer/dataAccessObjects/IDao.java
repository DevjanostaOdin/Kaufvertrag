package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.exceptions.DaoException;

import java.util.List;

/**
 * Generisches Interface für den Datenzugriff
 */
public interface IDao<T, K> {

    /**
     * Function name: create
     *
     * @return (T) Ein neu erstelltes Objekt vom Typ T.
     * @throws DaoException Gibt eine Fehlermeldung aus, wenn ein Fehler beim Einfügen auftritt.
     *
     * Inside the function: Erstellt ein neues Objekt vom Typ T.
     */
    T create() throws DaoException;

    /**
     * Function name: create
     *
     * @param objectToInsert (Das einzufügende Objekt vom Typ T.)
     * @throws DaoException Gibt eine Fehlermeldung aus, wenn ein Fehler beim Einfügen auftritt.
     *
     * Inside the function: Fügt ein spezifisches Objekt vom Typ T in die Datenquelle ein.
     */
    void create(T objectToInsert) throws DaoException;

    /**
     * Function name: read
     *
     * @param id (Die ID vom Typ K des zu lesenden Objekts.)
     * @return (T) Das gelesene Objekt vom Typ T.
     * @throws DaoException Gibt eine Fehlermeldung aus, wenn ein Fehler beim Lesen auftritt.
     *
     * Inside the function: Liest ein Objekt vom Typ T basierend auf seiner ID vom Typ K aus der Datenquelle.
     */
    T read(K id) throws DaoException;

    /**
     * Function name: readAll
     *
     * @return List<T> (Eine Liste von Objekten vom Typ T.)
     * @throws DaoException Gibt eine Fehlermeldung aus, wenn ein Fehler beim Lesen auftritt.
     *
     * Inside the function: Liest alle Objekte vom Typ T aus der Datenquelle.
     */
    List<T> readAll() throws DaoException;

    /**
     * Function name: update
     *
     * @param objectToUpdate (Das zu aktualisierende Objekt vom Typ T.)
     * @throws DaoException Gibt eine Fehlermeldung aus, wenn ein Fehler beim Aktualisieren auftritt.
     *
     * Inside the function: Aktualisiert ein spezifisches Objekt vom Typ T in der Datenquelle.
     */
    void update(T objectToUpdate) throws DaoException;

    /**
     * Function name: delete
     *
     * @param id (Die ID vom Typ K des zu löschenden Objekts.)
     * @throws DaoException Gibt eine Fehlermeldung aus, wenn ein Fehler beim Löschen auftritt.
     *
     * Inside the function: Löscht ein Objekt vom Typ T basierend auf seiner ID vom Typ K aus der Datenquelle.
     */
    void delete(K id) throws DaoException;
}
