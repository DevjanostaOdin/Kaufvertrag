package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;

public class ServiceXml {
    public final String DATEIPFAD = ".\\XML_Persistierung_Test.xml";

    public void createXmlDocument() throws IOException {
        Document document = new Document();
        Element root = new Element("Kaufvertrag");
        document.setRootElement(root);

        String datei = DATEIPFAD;
        FileOutputStream fileOutputStream = new FileOutputStream(datei);

        Format format = Format.getPrettyFormat();
        format.setIndent("    ");

        XMLOutputter xmlOutputter = new XMLOutputter(format);
        xmlOutputter.output(document, fileOutputStream);
    }

    public void addVertragspartner(IVertragspartner vertragspartner, Document document) {
        Element root = document.getRootElement();

        Element person = new Element("Vertragspartner");
        person.setAttribute("Ausweisnummer", vertragspartner.getAusweisNr());

        Element vorname = new Element("Vorname");
        vorname.addContent(vertragspartner.getVorname());
        person.addContent(vorname);

        Element nachname = new Element("Nachname");
        nachname.addContent(vertragspartner.getNachname());
        person.addContent(nachname);

        Element adresse = new Element("Adresse");
        person.addContent(adresse);

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

        root.addContent(person);
    }

    public Document loadXmlDocument(String filePath) throws JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        return saxBuilder.build(filePath);
    }

    public void saveXmlDocument(Document document) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(DATEIPFAD);

        Format format = Format.getPrettyFormat();
        format.setIndent("    ");

        XMLOutputter xmlOutputter = new XMLOutputter(format);
        xmlOutputter.output(document, fileOutputStream);
    }

    public void addWare(IWare ware, Document document) {
        Element root = document.getRootElement();

        Element zuVerkaufendeWare = new Element("Ware");
        zuVerkaufendeWare.setAttribute("Ausweisnummer", String.valueOf(ware.getId()));

        zuVerkaufendeWare.setAttribute("Bezeichnung", ware.getBezeichnung());
        Element beschreibung = new Element("Beschreibung");
        beschreibung.addContent(ware.getBeschreibung());
        zuVerkaufendeWare.addContent(beschreibung);
        Element preis = new Element("Preis");
        preis.addContent(String.valueOf(ware.getPreis()));
        zuVerkaufendeWare.addContent(preis);
        Element besonderheitenliste = new Element("Besonderheitenliste");
        for (var item : ware.getBesonderheiten()) {
            Element besonderheit = new Element("Besonderheit");
            besonderheit.addContent(item);
            besonderheitenliste.addContent(besonderheit);
        }
        zuVerkaufendeWare.addContent(besonderheitenliste);
        Element maengelListe = new Element("Maengelliste");
        for (var item : ware.getMaengel()) {
            Element mangel = new Element("Mangel");
            mangel.addContent(item);
            maengelListe.addContent(mangel);
        }
        zuVerkaufendeWare.addContent(maengelListe);
        root.addContent(zuVerkaufendeWare);
    }
}