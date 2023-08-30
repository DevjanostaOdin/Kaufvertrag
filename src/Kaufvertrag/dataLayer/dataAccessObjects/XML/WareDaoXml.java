package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;

import java.util.List;

public class WareDaoXml implements IDao<IWare, Long> {

    @Override
    public IWare create() {

        /*Document doc = new Document() {
        */


        return null;
    }

    //addcontent()
    //


    @Override
    public void create(IWare objectToInsert) {
        WareDaoXml xmlDatei = new WareDaoXml();

 /*       Element root = new Element()

        Document doc = xmlDatei.createDoc();*/



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
    public void update(IWare objectTpUpdate) {

    }

    @Override
    public void delete(Long id) {

    }
}
