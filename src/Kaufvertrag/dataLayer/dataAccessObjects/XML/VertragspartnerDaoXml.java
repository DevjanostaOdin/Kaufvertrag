package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.exceptions.DaoException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VertragspartnerDaoXml implements IDao<IVertragspartner, String> {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    IVertragspartner vertragspartner = new Vertragspartner();
    IAdresse adresse = new Adresse();
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

    @Override
    public IVertragspartner create() {
        return new Vertragspartner();
    }

    @Override
    public void create(IVertragspartner objectToInsert) {
        ServiceXml serviceXml = new ServiceXml();
        try {
            serviceXml.addVertragspartner(objectToInsert);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IVertragspartner read(String id) {
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(".\\XML_Persistierung.xml"));
            NodeList list = document.getElementsByTagName("Vertragspartner");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String ausweisnummer = element.getAttribute("Ausweisnummer");
                    if( ausweisnummer.equalsIgnoreCase(id)) {
                        String vorname = element.getElementsByTagName("Vorname").item(0).getTextContent();
                        String nachname = element.getElementsByTagName("Nachname").item(0).getTextContent();
                        String strasse = element.getElementsByTagName("Strasse").item(0).getTextContent();
                        String hausNr = element.getElementsByTagName("HausNr").item(0).getTextContent();
                        String plz = element.getElementsByTagName("Plz").item(0).getTextContent();
                        String ort = element.getElementsByTagName("Ort").item(0).getTextContent();

                        vertragspartner.setAusweisNr(id);
                        vertragspartner.setVorname(vorname);
                        vertragspartner.setNachname(nachname);
                        adresse.setStrasse(strasse);
                        adresse.setHausNr(hausNr);
                        adresse.setPlz(plz);
                        adresse.setOrt(ort);
                        vertragspartner.setAdresse(adresse);
                        break;
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return vertragspartner;
    }

    @Override
    public List<IVertragspartner> readAll() {
        List<IVertragspartner> vertragspartnerList = new ArrayList<>();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(".\\XML_Persistierung.xml"));
            NodeList list = document.getElementsByTagName("Vertragspartner");

            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String ausweisnummer = element.getAttribute("Ausweisnummer");
                    String vorname = element.getElementsByTagName("Vorname").item(0).getTextContent();
                    String nachname = element.getElementsByTagName("Nachname").item(0).getTextContent();
                    String strasse = element.getElementsByTagName("Strasse").item(0).getTextContent();
                    String hausNr = element.getElementsByTagName("HausNr").item(0).getTextContent();
                    String plz = element.getElementsByTagName("Plz").item(0).getTextContent();
                    String ort = element.getElementsByTagName("Ort").item(0).getTextContent();

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
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return vertragspartnerList;
    }

    @Override
    public void update(IVertragspartner objectToUpdate) {

        String id = objectToUpdate.getAusweisNr();
        delete(id);
        Scanner scanner = new Scanner(System.in);
        // delete danach create mit parametern ausführen
        System.out.print("Geben Sie den neuen Vornamen ein:");
        String vornameUpdated = scanner.nextLine();
        System.out.print("Geben Sie den neuen Nachnamen ein:");
        String nachnameUpdated = scanner.nextLine();
        System.out.print("Geben Sie die neue Strasse ein:");
        String strasseUpdated = scanner.nextLine();
        System.out.print("Geben Sie die neue Hausnummer ein:");
        String hausnrUpdated = scanner.nextLine();
        System.out.print("Geben Sie die neue PLZ ein:");
        String plzUpdated = scanner.nextLine();
        System.out.print("Geben Sie den neuen Ort ein:");
        String ortUpdated = scanner.nextLine();






 /*       try {
            // Holen Sie sich die Liste aller Vertragspartner
            List<IVertragspartner> vertragspartnerList = readAll();

            // Finden Sie den zu aktualisierenden Vertrhagspartner anhand der ID
            String idToUpdate = objectToUpdate.getAusweisNr(); // Angenommen, es gibt eine Methode getAusweisNr() in Ihrem IVertragspartner-Interface
            Vertragspartner vertragspartnerToUpdate = null;

            for (Vertragspartner vertragspartner : vertragspartnerList) {
                if (vertragspartner.getAusweisNr().equals(idToUpdate)) {
                    vertragspartnerToUpdate = vertragspartner;
                    break;
                }
            }

            // Wenn der zu aktualisierende Vertragspartner gefunden wurde, aktualisieren Sie ihn
            if (vertragspartnerToUpdate != null) {
                vertragspartnerToUpdate.setVorname(objectTpUpdate.getVorname());
                vertragspartnerToUpdate.setNachname(objectTpUpdate.getNachname());
                // usw. Aktualisieren Sie die übrigen Felder des Vertragspartners

                // Aktualisieren Sie dann das XML-Dokument, indem Sie die aktualisierte Liste der Vertragspartner speichern
                // Hier wird die Liste der Vertragspartner in XML konvertiert und in einer Datei gespeichert
                // Zum Speichern in XML kannst du eine geeignete Bibliothek wie z.B. JAXB oder DOM verwenden.
                // Beispiel (DOM):
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(new File(".\\XML_Persistierung.xml"));
                NodeList nodeList = document.getElementsByTagName("Vertragspartner");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        String ausweisnummer = element.getAttribute("Ausweisnummer");

                        if (ausweisnummer.equals(idToUpdate)) {
                            // Aktualisiere die XML-Daten basierend auf den aktualisierten Vertragspartner-Daten
                            element.getElementsByTagName("Vorname").item(0).setTextContent(vertragspartnerToUpdate.getVorname());
                            element.getElementsByTagName("Nachname").item(0).setTextContent(vertragspartnerToUpdate.getNachname());
                            // und so weiter für andere Felder
                        }
                    }
                }

                // Speichern Sie das aktualisierte Dokument zurück in die Datei
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(new File(".\\XML_Persistierung.xml"));
                transformer.transform(source, result);

                System.out.println("Vertragspartner mit ID: " + idToUpdate + " wurde erfolgreich aktualisiert.");
            } else {
                System.out.println("Vertragspartner mit ID: " + idToUpdate + " wurde nicht gefunden.");
            }
        } catch (Exception e) {
            System.out.println("Fehler beim Aktualisieren des Vertragspartners: " + e.getMessage());
        }
*/

    }

    @Override
    public void delete(String id) {

        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(".\\XML_Persistierung.xml"));
            NodeList list = document.getElementsByTagName("Vertragspartner");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String ausweisnummer = element.getAttribute("Ausweisnummer");
                    if( ausweisnummer.equalsIgnoreCase(id)) {
                        node.getParentNode().removeChild(node);
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(".\\XML_Persistierung.xml"));
            transformer.transform(source, result);

        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }
    }
}


