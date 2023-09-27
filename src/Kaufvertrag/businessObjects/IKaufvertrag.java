package Kaufvertrag.businessObjects;

/**
 * Dieses Interface stellt Methoden bereit, die das Abrufen und Bearbeiten von
 * Kaufvertrag-Objekten ermöglichen.
 */
public interface IKaufvertrag {
    /**
     * Function name: getVerkaeufer
     *
     * @return (IVertragspartner)
     *
     * Inside the function:
     *  1. Methode für das Abrufen eines Verkaeufer-Objektes.
     */
    IVertragspartner getVerkaeufer();

    /**
     * Function name: setVerkaeufer
     *
     * @param verkaeufer (IVertragspartner)
     *
     * Inside the function:
     *  1. Methode für das Setzen eines Verkaeufer-Objektes.
     */
    void setVerkaeufer(IVertragspartner verkaeufer);

    /**
     * Function name: getKaeufer
     *
     * @return (IVertragspartner)
     *
     * Inside the function:
     *  1. Methode für das Abrufen eines Kaeufer-Objektes.
     */
    IVertragspartner getKaeufer();

    /**
     * Function name: setKaeufer
     *
     * @param kaeufer (IVertragspartner)
     *
     * Inside the function:
     *  1. Methode für das Setzen eines Kaeufer-Objektes.
     */
    void setKaeufer(IVertragspartner kaeufer);

    /**
     * Function name: getWare
     *
     * @return (IWare)
     *
     * Inside the function:
     *  1. Methode für das Abrufen eines Ware-Objektes.
     */
    IWare getWare();

    /**
     * Function name: setWare
     *
     * @param ware (IWare)
     *
     * Inside the function:
     *  1. Methode für das Setzen eines Ware-Objektes.
     */
    void setWare(IWare ware);

    /**
     * Function name: getZahlungsModalitaeten
     *
     * @return (String)
     *
     * Inside the function:
     *  1. Methode für das Abrufen der Zahlungsmodalitaeten.
     */
    String getZahlungsModalitaeten();

    /**
     * Function name: setZahlungsModalitaeten
     *
     * @param zahlungsModalitaeten (String)
     *
     * Inside the function:
     *  1. Methode für das Setzen der Zahlungsmodalitaeten.
     */
    void setZahlungsModalitaeten(String zahlungsModalitaeten);
}