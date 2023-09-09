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
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
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
            System.out.println("Bitte geben Sie den Dateinamen an:");
            serviceXml.dateiName = br.readLine();
            serviceXml.CreateXmlDocument(objectToInsert);
//        } catch (DaoException eDao) {
//            eDao.printStackTrace();
        } catch (IOException eIo) {
            eIo.printStackTrace();
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
    public void update(IVertragspartner objectTpUpdate) {

    }

    @Override
    public void delete(String id) {

    }
}


