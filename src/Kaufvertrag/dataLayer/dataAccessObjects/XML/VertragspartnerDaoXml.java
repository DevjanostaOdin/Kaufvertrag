package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.exceptions.DaoException;

import java.io.BufferedReader;
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
