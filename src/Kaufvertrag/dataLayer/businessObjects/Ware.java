package Kaufvertrag.dataLayer.businessObjects;

import Kaufvertrag.businessObjects.IWare;

import java.util.List;

public class Ware implements IWare {
    long id;
    String bezeichnung;
    String beschreibung;
    double preis;
    List<String> besonderheiten;
    List<String> maengel;

    public Ware(String bezeichnung, double preis) {
        this.bezeichnung = bezeichnung;
        this.preis = preis;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ware " +
                "ID: " + id +
                ", Bezeichnung: '" + bezeichnung + '\'' +
                ", Beschreibung: '" + beschreibung + '\'' +
                ", Preis: " + preis +
                ", Besonderheiten: " + besonderheiten +
                ", Mängel: " + maengel +
                '.';
    }
}
