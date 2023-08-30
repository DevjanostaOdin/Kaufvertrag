import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.DataLayerManager;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;
import Kaufvertrag.dataLayer.dataAccessObjects.sqlite.DataLayerSqlite;
import Kaufvertrag.dataLayer.dataAccessObjects.sqlite.VertragspartnerDaoSqlite;

public class Programm {
    public static void main(String[] args) {


    IVertragspartner vertPart = new Vertragspartner("Snoopie", "Doggiee");
    vertPart.setAusweisNr("0987677700");

    IDao<IVertragspartner, String> dao = DataLayerManager.getInstance().getDataLayer().getDaoVertragspartner();
    dao.create(vertPart);

    }
}
