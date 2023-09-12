package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.businessObjects.Ware;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ServiceXml2 {

    public static final String DATEIPFAD = ".\\wareXml\\";
    //create bekommt fertiges objekt übergeben was dann mit service.xml methode writeXml() als Parameter erstellt wird

    //Methode write xml / XML dokument erzeugen mit Parameter das Objekt

    public void writeXml(IWare ware) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Ware.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(ware, new File(String.format("%sware%d.xml", DATEIPFAD, ware.getId())));
    }


    //Methode read xml / XML
    public IWare readXml(Long id) throws JAXBException, FileNotFoundException {
        String datei = String.format("%sware%d.xml", DATEIPFAD,id );
        JAXBContext context = JAXBContext.newInstance(Ware.class);
        return (Ware) context.createUnmarshaller()
                .unmarshal(new FileReader(datei));
    }


}
