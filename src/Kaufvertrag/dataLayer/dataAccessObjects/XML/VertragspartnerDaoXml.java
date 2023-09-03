package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IVertragspartner;
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
import java.util.List;
import java.util.Scanner;

public class VertragspartnerDaoXml implements IDao<IVertragspartner, String> {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

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
        IVertragspartner vertragspartner = new Vertragspartner();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        String dateiName;
        String dateiPfad = ".\\";
        try {
            System.out.println("Welche Datei möchten sie lesen?");
            dateiName = br.readLine();

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(dateiPfad + dateiName));

            NodeList list = document.getElementsByTagName("Vertragspartner");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    // get staff's attribute
                    id = element.getAttribute("Ausweisnummer");

                    // get text
                    String vorname = element.getElementsByTagName("Vorname").item(0).getTextContent();
                    String nachname = element.getElementsByTagName("Nachname").item(0).getTextContent();
                    String adresse = element.getElementsByTagName("Adresse").item(0).getTextContent();

                    //NodeList salaryNodeList = element.getElementsByTagName("salary");
                    //String salary = salaryNodeList.item(0).getTextContent();

                    // get salary's attribute
                    //String currency = salaryNodeList.item(0).getAttributes().getNamedItem("currency").getTextContent();

                    System.out.println("Current Element :" + node.getNodeName());
                    System.out.println("Ausweisnummer : " + id);
                    System.out.println("Vorname : " + vorname);
                    System.out.println("Nachname : " + nachname);
                    System.out.println("Adresse : " + adresse);
                    //System.out.printf("Salary [Currency] : %,.2f [%s]%n%n", Float.parseFloat(salary), currency);

                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return vertragspartner;
    }

    @Override
    public List<IVertragspartner> readAll() {
        return null;
    }

    @Override
    public void update(IVertragspartner objectTpUpdate) {

    }

    @Override
    public void delete(String id) {

    }
}
