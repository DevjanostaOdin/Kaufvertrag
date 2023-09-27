package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.exceptions.DaoException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;

public class ServiceXml {
    public final String DATEIPFAD = ".\\XML_Persistierung.xml";

    public void createXmlDocument() throws DaoException, IOException {
        try {
            Document document = new Document();
            Element root = new Element("Kaufvertrag");
            document.setRootElement(root);

            String datei = DATEIPFAD;
            FileOutputStream fileOutputStream = new FileOutputStream(datei);

            Format format = Format.getPrettyFormat();
            format.setIndent("    ");

            XMLOutputter xmlOutputter = new XMLOutputter(format);
            xmlOutputter.output(document, fileOutputStream);
        } catch (IOException e) {
            throw new DaoException("Fehler beim Erstellen des XML-Dokuments.");
        }
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

    public Document loadXmlDocument(String filePath) throws JDOMException, IOException, DaoException {
        try {
        SAXBuilder saxBuilder = new SAXBuilder();
        return saxBuilder.build(filePath);
        } catch (JDOMException | IOException e) {
            throw new DaoException("Fehler beim Laden des XML-Dokuments.");
        }
    }

    public void saveXmlDocument(Document document) throws DaoException {
        try {
        FileOutputStream fileOutputStream = new FileOutputStream(DATEIPFAD);

        Format format = Format.getPrettyFormat();
        format.setIndent("    ");

        XMLOutputter xmlOutputter = new XMLOutputter(format);
        xmlOutputter.output(document, fileOutputStream);
        } catch (IOException e) {
            throw new DaoException("Fehler beim Speichern des XML-Dokuments.");
        }
    }
}