import Kaufvertrag.dataLayer.dataAccessObjects.DataLayerManager;
import Kaufvertrag.dataLayer.dataAccessObjects.IDataLayer;
import Kaufvertrag.exceptions.DaoException;

public class Programm {
    public static void main(String[] args) {

        DataLayerManager dataLayerManager = DataLayerManager.getInstance();

        try {
            dataLayerManager.getDataLayer();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

    }
}
