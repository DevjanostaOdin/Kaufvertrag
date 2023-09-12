package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.businessObjects.Ware;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WareDaoXml implements IDao<IWare, Long> {
    List<Ware> waren = new ArrayList<>();
    ServiceXml2 serviceXml2 = new ServiceXml2();

/*    private List<Ware> setNextId(List<Ware> originalWarenXml) {

        int warenXmlSize = this.waren.size();
        if (warenXmlSize < 2) {
            waren.get(warenXmlSize - 1).setId(1);
        } else {
            long lastID = waren.get(warenXmlSize - 2).getId();
            waren.get(warenXmlSize - 1).setId(lastID + 1);
        }

        return waren;
    }*/

    @Override
    public IWare create() {
        return new Ware();
    }

    @Override
    public void create(IWare objectToInsert) {
        Ware ware = (Ware) objectToInsert;
        int nextId = waren.size() + 1;
        ware.setId(nextId);

        try {
            serviceXml2.writeXml(ware);
            waren.add(ware);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IWare read(Long id) {
        try {
            return serviceXml2.readXml(id);

        } catch (IOException | JAXBException e) {
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
        ServiceXml2 serviceXml2 = new ServiceXml2();
        try {
            serviceXml2.writeXml(objectTpUpdate);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        File xmlFile = new File(String.format("\\wareXml\\ware%d.xml", id));
        if (xmlFile.exists()) {
            xmlFile.delete();
        } else {
            System.out.println("file exestiert nicht");
        }
        waren.removeIf(ware -> (ware.getId() == id));
    }

}