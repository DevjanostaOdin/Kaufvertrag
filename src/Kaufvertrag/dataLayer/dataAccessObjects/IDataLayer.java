package Kaufvertrag.dataLayer.dataAccessObjects;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;

public interface IDataLayer  {
    IDao<IVertragspartner, String> getDaoVertragspartner();
    IDao<IWare, Long> getDaoWare();
}
