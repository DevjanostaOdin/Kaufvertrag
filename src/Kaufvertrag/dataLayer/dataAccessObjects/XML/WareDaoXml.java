package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.exceptions.DaoException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WareDaoXml implements IDao<IWare, Long> {

    private static final String DATEIPFAD = ".\\XML_Persistierung.xml";

    @Override
    public IWare create() {
        return null;
    }

    @Override
    public void create(IWare ware) throws DaoException {
        try {
            Document doc = loadXmlDocument(DATEIPFAD);
            Element root = doc.getRootElement();

            if (ware.getId() == 0) {
                ((Ware) ware).setId(generateNextId());
            }

            Element wareElement = new Element("Ware");
            wareElement.setAttribute("id", String.valueOf(ware.getId()));
            wareElement.addContent(new Element("Bezeichnung").setText(ware.getBezeichnung()));
            wareElement.addContent(new Element("Beschreibung").setText(ware.getBeschreibung()));
            wareElement.addContent(new Element("Preis").setText(String.valueOf(ware.getPreis())));

            Element besonderheitenliste = new Element("Besonderheitenliste");
            for (String item : ware.getBesonderheiten()) {
                Element besonderheit = new Element("Besonderheit");
                besonderheit.addContent(item);
                besonderheitenliste.addContent(besonderheit);
            }
            wareElement.addContent(besonderheitenliste);
            Element maengelListe = new Element("Maengelliste");
            for (String item : ware.getMaengel()) {
                Element mangel = new Element("Mangel");
                mangel.addContent(item);
                maengelListe.addContent(mangel);
            }
            wareElement.addContent(maengelListe);

            root.addContent(wareElement);

            saveXmlDocument(doc);

        } catch (Exception e) {
            throw new DaoException("Fehler beim Speichern der Ware" + e.getMessage());
        }
    }

    @Override
    public IWare read(Long id) throws DaoException {
        try {
            Document doc = loadXmlDocument(DATEIPFAD);
            Element root = doc.getRootElement();
            for (Element wareElement : root.getChildren("Ware")) {
                if (Long.parseLong(wareElement.getAttributeValue("id")) == id) {
                    Ware ware = new Ware();
                    ware.setId(id);
                    ware.setBezeichnung(wareElement.getChildText("Bezeichnung"));
                    ware.setBeschreibung(wareElement.getChildText("Beschreibung"));
                    ware.setPreis(Double.parseDouble(wareElement.getChildText("Preis")));

                    List<String> besonderheiten = new ArrayList<>();
                    for (Element besonderheitElement : wareElement.getChild("Besonderheitenliste").getChildren("Besonderheit")) {
                        besonderheiten.add(besonderheitElement.getText());
                    }
                    ware.setBesonderheiten(besonderheiten);

                    List<String> maengel = new ArrayList<>();
                    for (Element mangelElement : wareElement.getChild("Maengelliste").getChildren("Mangel")) {
                        maengel.add(mangelElement.getText());
                    }
                    ware.setMaengel(maengel);
                    return ware;
                }
            }
        } catch (Exception e) {
            throw new DaoException("Fehler beim Finden der Ware" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<IWare> readAll() throws DaoException {
        List<IWare> warenList = new ArrayList<>();
        try {
            Document doc = loadXmlDocument(DATEIPFAD);
            Element root = doc.getRootElement();
            for (Element wareElement : root.getChildren("Ware")) {
                Ware ware = new Ware();
                if (wareElement.getAttributeValue("id") == null) {
                    ware.setId(0);
                }
                ware.setId(Long.parseLong(wareElement.getAttributeValue("id")));
                ware.setBezeichnung(wareElement.getChildText("Bezeichnung"));
                ware.setBeschreibung(wareElement.getChildText("Beschreibung"));
                ware.setPreis(Double.parseDouble(wareElement.getChildText("Preis")));

                List<String> besonderheiten = new ArrayList<>();
                for (Element besonderheitElement : wareElement.getChild("Besonderheitenliste").getChildren("Besonderheit")) {
                    besonderheiten.add(besonderheitElement.getText());
                }
                ware.setBesonderheiten(besonderheiten);

                List<String> maengel = new ArrayList<>();
                for (Element mangelElement : wareElement.getChild("Maengelliste").getChildren("Mangel")) {
                    maengel.add(mangelElement.getText());
                }
                ware.setMaengel(maengel);
                warenList.add(ware);
            }
        } catch (Exception e) {
            throw new DaoException("Fehler beim Abrufen der Warenliste" + e.getMessage());
        }
        return warenList;
    }

    @Override
    public void update(IWare objectToUpdate) throws DaoException {
        try {
            Document doc = loadXmlDocument(DATEIPFAD);
            Element root = doc.getRootElement();

            Element toUpdate = null;
            for (Element wareElement : root.getChildren("Ware")) {
                if (Long.parseLong(wareElement.getAttributeValue("id")) == objectToUpdate.getId()) {
                    toUpdate = wareElement;
                    break;
                }
            }

            if (toUpdate != null) {
                toUpdate.getChild("Bezeichnung").setText(objectToUpdate.getBezeichnung());
                toUpdate.getChild("Beschreibung").setText(objectToUpdate.getBeschreibung());
                toUpdate.getChild("Preis").setText(String.valueOf(objectToUpdate.getPreis()));

                Element besonderheitenliste = toUpdate.getChild("Besonderheitenliste");
                besonderheitenliste.removeContent();
                for (String item : objectToUpdate.getBesonderheiten()) {
                    Element besonderheit = new Element("Besonderheit");
                    besonderheit.addContent(item);
                    besonderheitenliste.addContent(besonderheit);
                }

                Element maengelListe = toUpdate.getChild("Maengelliste");
                maengelListe.removeContent();
                for (String item : objectToUpdate.getMaengel()) {
                    Element mangel = new Element("Mangel");
                    mangel.addContent(item);
                    maengelListe.addContent(mangel);
                }

                saveXmlDocument(doc);
            } else {
                throw new DaoException("Ware mit der ID " + objectToUpdate.getId() + " nicht gefunden.");
            }
        } catch (Exception e) {
            throw new DaoException("Fehler beim Aktualisieren der Ware" + e.getMessage());
        }
    }


    @Override
    public void delete(Long id) throws DaoException {
        IWare ware = read(id);
        try {
            Document doc = loadXmlDocument(DATEIPFAD);
            Element root = doc.getRootElement();
            Element toDelete = null;
            for (Element wareElement : root.getChildren("Ware")) {
                if (Long.parseLong(wareElement.getAttributeValue("id")) == ware.getId()) {
                    toDelete = wareElement;
                    break;
                }
            }
            if (toDelete != null) {
                root.removeContent(toDelete);
                saveXmlDocument(doc);
            }
        } catch (Exception e) {
            throw new DaoException("Fehler beim Löschen der Ware" + e.getMessage());
        }
    }

    private long generateNextId() throws DaoException {
        List<IWare> waren = readAll();
        long highestId = 0;
        for (IWare ware : waren) {
            if (ware.getId() > highestId) {
                highestId = ware.getId();
            }
        }
        return highestId + 1;
    }

    private Document loadXmlDocument(String filePath) throws JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        return saxBuilder.build(new File(filePath));
    }

    private void saveXmlDocument(Document document) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(DATEIPFAD);

        Format format = Format.getPrettyFormat();
        format.setIndent("    ");

        XMLOutputter xmlOutputter = new XMLOutputter(format);
        xmlOutputter.output(document, fileOutputStream);
    }
}
