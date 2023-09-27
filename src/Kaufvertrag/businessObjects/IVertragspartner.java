package Kaufvertrag.businessObjects;

/**
 * Dieses Interface stellt Methoden bereit, die das Abrufen und Bearbeiten von
 * Vertragspartner-Objekten ermöglichen.
 */
public interface IVertragspartner {
    /**
     * Function name: getAusweisNr
     *
     * @return (String)
     *
     * Inside the function:
     *  1. Methode für das Abrufen einer Ausweisnummer.
     */
    String getAusweisNr();

    /**
     * Function name: setAusweisNr
     *
     * @param ausweisNr (String)
     *
     * Inside the function:
     *  1. Methode für das Setzen der Ausweisnummer.
     */
    void setAusweisNr(String ausweisNr);

    /**
     * Function name: getVorname
     *
     * @return (String)
     *
     * Inside the function:
     *  1. Methode für das Abrufen eines Vornamens.
     */
    String getVorname();

    /**
     * Function name: setVorname
     *
     * @param vorname (String)
     *
     * Inside the function:
     *  1. Methode für das Setzen des Vornamens.
     */
    void setVorname(String vorname);

    /**
     * Function name: getNachname
     *
     * @return (String)
     *
     * Inside the function:
     *  1. Methode für das Abrufen eines Nachnamens.
     */
    String getNachname();

    /**
     * Function name: setNachname
     *
     * @param nachname (String)
     *
     * Inside the function:
     *  1. Methode für das Setzen des Nachnamens.
     */
    void setNachname(String nachname);

    /**
     * Function name: getAdresse
     *
     * @return (String)
     *
     * Inside the function:
     *  1. Methode für das Abrufen der Adresse.
     */
    IAdresse getAdresse();

    /**
     * Function name: setAdresse
     *
     * @param adresse (IAdresse)
     *
     * Inside the function:
     *  1. Methode für das Setzen der Adresse.
     */
    void setAdresse(IAdresse adresse);
}