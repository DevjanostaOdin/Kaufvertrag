package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.exceptions.DaoException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class VertragspartnerDaoXml implements IDao<IVertragspartner, String> {
    @Override
    public IVertragspartner create() {
        return new Vertragspartner();
    }

    @Override
    public void create(IVertragspartner objectToInsert) {
        ServiceXml serviceXml = new ServiceXml();
        try {
            Document document = serviceXml.loadXmlDocument(serviceXml.DATEIPFAD);
            serviceXml.addVertragspartner(objectToInsert, document);
            serviceXml.saveXmlDocument(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IVertragspartner read(String id) {
        ServiceXml serviceXml = new ServiceXml();
        try {
            Document document = serviceXml.loadXmlDocument(serviceXml.DATEIPFAD);
            Element root = document.getRootElement();
            List<Element> vertragspartnerElements = root.getChildren("Vertragspartner");

            for (Element element : vertragspartnerElements) {
                String ausweisnummer = element.getAttributeValue("Ausweisnummer");
                if (ausweisnummer.equalsIgnoreCase(id)) {
                    String vorname = element.getChildText("Vorname");
                    String nachname = element.getChildText("Nachname");

                    Vertragspartner vertragspartner = new Vertragspartner();
                    vertragspartner.setAusweisNr(id);
                    vertragspartner.setVorname(vorname);
                    vertragspartner.setNachname(nachname);

                    Adresse adresse = new Adresse();
                    Element adresseElement = element.getChild("Adresse");
                    adresse.setStrasse(adresseElement.getChildText("Strasse"));
                    adresse.setHausNr(adresseElement.getChildText("HausNr"));
                    adresse.setPlz(adresseElement.getChildText("Plz"));
                    adresse.setOrt(adresseElement.getChildText("Ort"));

                    vertragspartner.setAdresse(adresse);

                    return vertragspartner;
                }
            }
        } catch (IOException | JDOMException | DaoException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<IVertragspartner> readAll() {
        List<IVertragspartner> vertragspartnerList = new ArrayList<>();
        ServiceXml serviceXml = new ServiceXml();
        try {
            Document document = serviceXml.loadXmlDocument(serviceXml.DATEIPFAD);
            Element root = document.getRootElement();
            List<Element> vertragspartnerElements = root.getChildren("Vertragspartner");

            for (Element element : vertragspartnerElements) {
                String ausweisnummer = element.getAttributeValue("Ausweisnummer");
                String vorname = element.getChildText("Vorname");
                String nachname = element.getChildText("Nachname");
                Element adresseElement = element.getChild("Adresse");
                String strasse = adresseElement.getChildText("Strasse");
                String hausNr = adresseElement.getChildText("HausNr");
                String plz = adresseElement.getChildText("Plz");
                String ort = adresseElement.getChildText("Ort");

                Vertragspartner vertragspartner = new Vertragspartner();
                vertragspartner.setAusweisNr(ausweisnummer);
                vertragspartner.setVorname(vorname);
                vertragspartner.setNachname(nachname);

                Adresse adresse = new Adresse();
                adresse.setStrasse(strasse);
                adresse.setHausNr(hausNr);
                adresse.setPlz(plz);
                adresse.setOrt(ort);

                vertragspartner.setAdresse(adresse);

                vertragspartnerList.add(vertragspartner);
            }
        } catch (IOException | JDOMException | DaoException e) {
            e.printStackTrace();
        }
        return vertragspartnerList;
    }

    @Override
    public void update(IVertragspartner objectToUpdate) {

        String id = objectToUpdate.getAusweisNr();
        delete(id);
        create(objectToUpdate);

    }

    @Override
    public void delete(String id) {
        ServiceXml serviceXml = new ServiceXml();
        try {
            Document document = serviceXml.loadXmlDocument(serviceXml.DATEIPFAD);
            Element root = document.getRootElement();
            List<Element> vertragspartnerElements = root.getChildren("Vertragspartner");

            for (Element element : vertragspartnerElements) {
                String ausweisnummer = element.getAttributeValue("Ausweisnummer");
                if (ausweisnummer.equalsIgnoreCase(id)) {
                    root.removeContent(element);
                    break;
                }
            }
            serviceXml.saveXmlDocument(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}