package Kaufvertrag.dataLayer.dataAccessObjects.XML;

import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.businessObjects.IWare;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;

public class DataLayerXml implements IDataLayer {
    @Override
    public IDao<IVertragspartner, String> getDaoVertragspartner() {
        return null;
    }

    @Override
    public IDao<IWare, Long> getDaoWare() {

        return null;
    }
}
