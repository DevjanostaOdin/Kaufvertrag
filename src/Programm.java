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


   IVertragspartner vertPart = new Vertragspartner("100", "Dollar");
    vertPart.setAusweisNr("0111111");
    IAdresse adresse = new Adresse("Danzigerstr","10","66666","Hamburg");
    vertPart.setAdresse(adresse);

   IDao<IVertragspartner, String> dao = DataLayerManager.getInstance().getDataLayer().getDaoVertragspartner();

   //dao.create(vertPart);
   //dao.update(vertPart);
   dao.read("8");
   //dao.delete("VERTRAGSPARTNER");
   //dao.delete("ADRESSE");
    }
}
