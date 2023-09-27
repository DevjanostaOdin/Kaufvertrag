package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.exceptions.DaoException;

/**
 * Interface zur Definition von Datenzugriffsmethoden für verschiedene Geschäftsobjekte.
 */
public interface IDataLayer {
    /**
     * Function name: getDaoVertragspartner
     *
     * @return (IVertragspartner) Ein DAO-Objekt für Vertragspartner.
     * Inside the function: Liefert ein Data Access Object (DAO) für das Geschäftsobjekt Vertragspartner zurück.
     * @throws DaoException Gibt eine Fehlermeldung aus, falls beim Abrufen des DAO ein Fehler auftritt.
     */
    IDao<IVertragspartner, String> getDaoVertragspartner() throws DaoException;

    /**
     * Function name: getDaoWare
     *
     * @return (IWare) Ein DAO-Objekt für Ware.
     * Inside the function: Liefert ein Data Access Object (DAO) für das Geschäftsobjekt Ware zurück.
     * @throws DaoException Gibt eine Fehlermeldung aus, falls beim Abrufen des DAO ein Fehler auftritt.
     */
    IDao<IWare, Long> getDaoWare() throws DaoException;
}