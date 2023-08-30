import Kaufvertrag.businessObjects.IAdresse;
import Kaufvertrag.businessObjects.IVertragspartner;
import Kaufvertrag.dataLayer.businessObjects.Adresse;
import Kaufvertrag.dataLayer.businessObjects.Vertragspartner;
import Kaufvertrag.dataLayer.dataAccessObjects.DataLayerManager;
import Kaufvertrag.dataLayer.dataAccessObjects.IDao;
import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;
import Kaufvertrag.dataLayer.dataAccessObjects.sqlite.DataLayerSqlite;
import Kaufvertrag.dataLayer.dataAccessObjects.sqlite.VertragspartnerDaoSqlite;

public class Programm {
    public static void main(String[] args) {


   IVertragspartner vertPart = new Vertragspartner("500", "Euro");
    vertPart.setAusweisNr("09876777002888");
    IAdresse adresse = new Adresse("Berlinerstr","1","77777","Berlin");
    vertPart.setAdresse(adresse);

   IDao<IVertragspartner, String> dao = DataLayerManager.getInstance().getDataLayer().getDaoVertragspartner();

   dao.create(vertPart);
   //dao.delete("VERTRAGSPARTNER");
    }
}
