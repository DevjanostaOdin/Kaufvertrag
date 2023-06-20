package Kaufvertrag.dataLayer.dataAccessObjects;

public interface IDataLayer  {
    IDao<IVertragspartner, String> getDaoVertragspartner();
    IDao<IWare, Long> getDaoWare();
}
