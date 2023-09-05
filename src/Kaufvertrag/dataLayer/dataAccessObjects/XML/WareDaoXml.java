package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WareDaoXml implements IDao<IWare, Long> {

    @Override
    public IWare create() {
        return null;
    }
    @Override
    public void create(IWare objectToInsert)  {

        Document doc = new Document();
        doc.setRootElement(new Element("Ware"));

        Element ware = new Element("Ware");
        Element bezeichnung = new Element("Bezeichnung");
        bezeichnung.setText(objectToInsert.getBezeichnung());
        Element beschreibung = new Element("Beschreibung");
        beschreibung.setText(objectToInsert.getBeschreibung());
        Element preis = new Element("Preis");
        preis.setText(String.valueOf(objectToInsert.getPreis()));
        Element besonderheiten = new Element("Besonderheiten");
        besonderheiten.setText(objectToInsert.getBesonderheiten().toString());
        Element maengel = new Element("Maengel");
        maengel.setText(objectToInsert.getMaengel().toString());
        ware.addContent(bezeichnung);
        ware.addContent(beschreibung);
        ware.addContent(preis);
        ware.addContent(besonderheiten);
        ware.addContent(maengel);

        doc.getRootElement().addContent(ware);

       try {
           String datei = String.format("C:\\Users\\sly\\IdeaProjects\\Kaufvertrag\\XMLDateien\\Ware%s.xml", objectToInsert.getId());
           FileOutputStream fileOutputStream = new FileOutputStream(datei);

           Format format = Format.getPrettyFormat();
           format.setIndent("    ");

           XMLOutputter xmlOutputter = new XMLOutputter();

           xmlOutputter.setFormat(format);
           xmlOutputter.output(doc, fileOutputStream);


           // Hier sollte laut Klassendiagramm die ServicXml Klasse aufgerufen werden die eine exception wirft
        } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public IWare read(Long id) {
        return null;
    }

    @Override
    public List<IWare> readAll() {
        return null;
    }

    @Override
    public void update(IWare objectToUpdate) {

    }

    @Override
    public void delete(Long id) {

    }
}
