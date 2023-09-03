package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.exceptions.DaoException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class VertragspartnerDaoXml implements IDao<IVertragspartner, String> {

    @Override
    public IVertragspartner create() {
        return new Vertragspartner();
    }

    @Override
    public void create(IVertragspartner objectToInsert) {
        ServiceXml serviceXml = new ServiceXml();
        try {
        serviceXml.CreateXmlDocument(objectToInsert);
//        } catch (DaoException eDao) {
//            eDao.printStackTrace();
        } catch (IOException eIo) {
            eIo.printStackTrace();
        }
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
