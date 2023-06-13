package Kaufvertrag.dataLayer.businessObjects;

import Kaufvertrag.businessObjects.IVertragspartner;

public class Vertragspartner implements IVertragspartner {
    public String rolle;
    public String ausweisNr;
    public String vorname;
    public String nachname;
    public Adresse adresse;

    public Vertragspartner(String rolle, String ausweisNr, String vorname, String nachname, Adresse adresse) {
        this.rolle = rolle;
        this.ausweisNr = ausweisNr;
        this.vorname = vorname;
        this.nachname = nachname;
        this.adresse = adresse;
    }
}
