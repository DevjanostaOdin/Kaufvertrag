package Kaufvertrag.businessObjects;

/**
 * Dieses Interface stellt Methoden bereit, die das Abrufen und Bearbeiten von
 * Adresse-Objekten ermöglichen.
 */
public interface IAdresse {

    /**
     * Function name: getStrasse
     *
     * @return (String)
     *
     * Inside the function:
     *  1. Methode für das Abrufen einer Strasse.
     */
    String getStrasse();

    /**
     * Function name: setStrasse
     *
     * @param strasse (String)
     *
     * Inside the function:
     *  1. Methode für das Setzen der Strasse.
     */
    void setStrasse(String strasse);

    /**
     * Function name: getHausNr
     *
     * @return (String)
     *
     * Inside the fucntion:
     *  1. Methode für das Abrufen einer Hausnummer.
     */
    String getHausNr();

    /**
     * Function name: setHausNr
     *
     * @param hausNr (String)
     *
     * Inside the function:
     *  1. Methode für das Setzen der Hausnummer.
     */
    void setHausNr(String hausNr);

    /**
     * Function name: getPlz
     *
     * @return (String)
     *
     * Inside the function:
     *  1. Methode für das Abrufen einer Postleitzahl.
     */
    String getPlz();

    /**
     * Function name: setPlz
     *
     * @param plz (String)
     *
     * Inside the function:
     *  1. Methode für das Setzen der Postleitzahl.
     */
    void setPlz(String plz);

    /**
     * Function name: getOrt
     *
     * @return (String)
     *
     * Inside the function:
     *  1. Methode für das Abrufen eines Ortes.
     */
    String getOrt();

    /**
     * Function name: setOrt
     *
     * @param ort (String)
     *
     * Inside the function:
     *  1. Methode für das Setzen des Ortes.
     */
    void setOrt(String ort);

    /**
     * Function name: toString
     *
     * @return (String)
     *
     * Inside the function:
     *  1. Stellt die Properties des Objektes in Textform dar.
     */
    String toString();
}