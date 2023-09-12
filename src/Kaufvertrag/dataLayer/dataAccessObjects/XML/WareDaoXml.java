package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.List;


public class WareDaoXml implements IDao<IWare, Long> {

    @Override
    public IWare create() {
        return new Ware();
    }

    @Override
    public void create(IWare objectToInsert) {
        ServiceXml serviceXml = new ServiceXml();
        try {
            Document document = serviceXml.loadXmlDocument(serviceXml.DATEIPFAD);
            serviceXml.addWare(objectToInsert, document);
            serviceXml.saveXmlDocument(document);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IWare read(Long id) {
        ServiceXml serviceXml = new ServiceXml();
        try {
            Document document = serviceXml.loadXmlDocument(serviceXml.DATEIPFAD);
            Element root = document.getRootElement();
            List<Element> wareElements = root.getChildren("Ware");

            for (Element element : wareElements) {
                String id1 = element.getAttributeValue("ID");
                if (id1.equalsIgnoreCase(String.valueOf(id))) {
                    String bezeichnung = element.getChildText("Bezeichnung");
                    String beschreibung = element.getChildText("Beschreibung");
                    String preis = element.getChildText("Preis");
                    String besonderheitenListe = element.getChildText("Besonderheitenliste");

                    Ware ware = new Ware();
                    ware.setId(id);
                    ware.setBezeichnung(bezeichnung);
                    ware.setBeschreibung(beschreibung);
                    ware.setPreis(Double.parseDouble(preis));/*
                    ware.setBesonderheiten(besonderheitenListe);*/




                    return ware;
                }
            }
        } catch (IOException | JDOMException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<IWare> readAll() {
        return null;
    }

    @Override
    public void update(IWare objectTpUpdate) {

    }

    @Override
    public void delete(Long id) {
/*        ServiceXml serviceXml = new ServiceXml();
        try {
            Document document = serviceXml.loadXmlDocument(serviceXml.DATEIPFAD);
            Element root = document.getRootElement();
            List<Element> vertragspartnerElements = root.getChildren("Ware");

            for (Element element : vertragspartnerElements) {
                String id1 = element.getAttributeValue("ID");
                if (ausweisnummer.equalsIgnoreCase(id)) {
                    root.removeContent(element);
                    break;
                }
            }
            serviceXml.saveXmlDocument(document);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

}