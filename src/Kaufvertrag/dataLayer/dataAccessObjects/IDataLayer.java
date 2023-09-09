package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.exceptions.DaoException;

public interface IDataLayer {
    IDao<IVertragspartner, String> getDaoVertragspartner() throws DaoException;
    IDao<IWare, Long> getDaoWare() throws DaoException;
}