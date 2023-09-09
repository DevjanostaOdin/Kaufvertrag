package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IVertragspartner;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ServiceXml {
    public String dateiName;

    private String dateiPfad = ".\\";

    public void createXmlDocument() throws IOException {
        Document document = new Document();
        Element root = new Element("Kaufvertrag");
        document.setRootElement(root);
        String datei = ".\\XML_Persistierung_Test.xml";
        FileOutputStream fileOutputStream = new FileOutputStream(datei);

        Format format = Format.getPrettyFormat();
        format.setIndent("    ");

        XMLOutputter xmlOutputter = new XMLOutputter(format);
        xmlOutputter.output(document, fileOutputStream);

    }

    public void createXmlDocument(IVertragspartner vertragspartner) throws IOException {
        Document document = new Document();
        Element root = new Element("Kaufvertrag");
        document.setRootElement(root);

        document.getRootElement().addContent(addVertragspartner(vertragspartner));

//        document.getRootElement().addContent(AddWare(kaufvertrag.ware));
//        document.getRootElement().addContent(ZahlungsMethodeHinzufuegen(kaufvertrag.zahlung));

        String datei = dateiPfad + dateiName + ".xml";
        FileOutputStream fileOutputStream = new FileOutputStream(datei);

        Format format = Format.getPrettyFormat();
        format.setIndent("    ");

        XMLOutputter xmlOutputter = new XMLOutputter(format);
        xmlOutputter.output(document, fileOutputStream);
    }

    public Element addVertragspartner(IVertragspartner vertragspartner) {
        Element Person = new Element("Vertragspartner");
        Person.setAttribute("Ausweisnummer" ,vertragspartner.getAusweisNr());
        Element vorname = new Element("Vorname");
        vorname.addContent(vertragspartner.getVorname());
        Person.addContent(vorname);
        Element nachname = new Element("Nachname");
        nachname.addContent(vertragspartner.getNachname());
        Person.addContent(nachname);
        Element adresse = new Element("Adresse");
        Person.addContent(adresse);
        Element strasse = new Element("Strasse");
        strasse.addContent(vertragspartner.getAdresse().getStrasse());
        adresse.addContent(strasse);
        Element hausNr = new Element("HausNr");
        hausNr.addContent(vertragspartner.getAdresse().getHausNr());
        adresse.addContent(hausNr);
        Element plz = new Element("Plz");
        plz.addContent(vertragspartner.getAdresse().getPlz());
        adresse.addContent(plz);
        Element ort = new Element("Ort");
        ort.addContent(vertragspartner.getAdresse().getOrt());
        adresse.addContent(ort);

        return Person;
    }

    //public Element AddWare(IWare ware) {
//        Element zuVerkaufendeWare = new Element("Ware");
//        zuVerkaufendeWare.setAttribute("Bezeichnung", ware.bezeichnung);
//        Element beschreibung = new Element("Beschreibung");
//        beschreibung.addContent(ware.beschreibung);
//        zuVerkaufendeWare.addContent(beschreibung);
//        Element preis = new Element("Preis");
//        preis.addContent(ware.preis);
//        zuVerkaufendeWare.addContent(preis);
//        Element besonderheitenliste = new Element("Besonderheitenliste");
//        for (var item : ware.besonderheitenListe) {
//            Element besonderheit = new Element("Besonderheit");
//            besonderheit.addContent(item);
//            besonderheitenliste.addContent(besonderheit);
//        }
//        zuVerkaufendeWare.addContent(besonderheitenliste);
//        Element maengelListe = new Element("Maengelliste");
//        for (var item : ware.maengelListe) {
//            Element mangel = new Element("Mangel");
//            mangel.addContent(item);
//            maengelListe.addContent(mangel);
//        }
//        zuVerkaufendeWare.addContent(maengelListe);
//        return zuVerkaufendeWare;
//    }
}
