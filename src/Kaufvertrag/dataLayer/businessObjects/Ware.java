package Kaufvertrag.dataLayer.businessObjects;

import Kaufvertrag.businessObjects.IWare;
import java.util.List;

public class Ware implements IWare {
    private long id;
    private String bezeichnung;
    private String beschreibung;
    private double preis;
    private List<String> besonderheiten;
    private List<String> maengel;

    public Ware() {
    }

    public Ware(String bezeichnung, double preis) {
        this.bezeichnung = bezeichnung;
        this.preis = preis;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getBezeichnung() {
        return bezeichnung;
    }

    @Override
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    @Override
    public String getBeschreibung() {
        return beschreibung;
    }

    @Override
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    @Override
    public double getPreis() {
        return preis;
    }

    @Override
    public void setPreis(double preis) {
        this.preis = preis;
    }

    @Override
    public List<String> getBesonderheiten() {
        return besonderheiten;
    }

    @Override
    public void setBesonderheiten(List<String> besonderheiten) {
        this.besonderheiten = besonderheiten;
    }

    @Override
    public List<String> getMaengel() {
        return maengel;
    }

    @Override
    public void setMaengel(List<String> maengel) {
        this.maengel = maengel;
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