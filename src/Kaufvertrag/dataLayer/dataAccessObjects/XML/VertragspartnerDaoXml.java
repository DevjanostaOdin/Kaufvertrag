package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;

import java.util.List;

public class VertragspartnerDaoXml implements IDao<IVertragspartner,String> {


    @Override
    public IVertragspartner create() {
        return null;
    }

    @Override
    public void create(IVertragspartner objectToInsert) {

    }

    @Override
    public IVertragspartner read(String id) {
        return null;
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
