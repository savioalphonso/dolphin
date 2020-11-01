package ca.sitkalabs.radar.AppStores;

import ca.sitkalabs.radar.Models.app.AndroidApp;
import ca.sitkalabs.radar.Models.app.App;
import ca.sitkalabs.radar.Models.device.VirtualDevice;
import ca.sitkalabs.radar.Models.packages.AppPackageFile;
import ca.sitkalabs.radar.Models.store.AppDetails;
import ca.sitkalabs.radar.Models.store.PlayStoreWorker;
import ca.sitkalabs.radar.Models.store.StoreWorker;
import ca.sitkalabs.radar.WorkerPool.StoreWorkerPool;
import com.dragons.aurora.playstoreapiv2.AndroidAppDeliveryData;
import com.dragons.aurora.playstoreapiv2.BuyResponse;
import com.dragons.aurora.playstoreapiv2.DeliveryResponse;
import com.dragons.aurora.playstoreapiv2.GooglePlayAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.CompletableFuture;

public class GooglePlayStore implements StoreAPI<Integer>{

    private final StoreWorkerPool workers;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public GooglePlayStore(){
        workers = new StoreWorkerPool();
    }


    public AndroidAppDeliveryData fetchDownloadData(AndroidApp app){

        StoreWorker worker = getInstance();
        AndroidAppDeliveryData deliveryData = getDeliveryData(app, worker.getApi());
        workers.returnObject(worker);
        return deliveryData;
    }

    private AndroidAppDeliveryData getDeliveryData(AndroidApp app, GooglePlayAPI api){
        try {
            BuyResponse buyResponse = api.purchase(app.getPackageName(), 0, 1);
            if (buyResponse.hasPurchaseStatusResponse()
                    && buyResponse.getPurchaseStatusResponse().hasAppDeliveryData()
                    && buyResponse.getPurchaseStatusResponse().getAppDeliveryData().hasDownloadUrl()) {
                return buyResponse.getPurchaseStatusResponse().getAppDeliveryData();
            }
            if (buyResponse.hasDownloadToken()) {
                String downloadToken = buyResponse.getDownloadToken();
                return delivery(app, api, downloadToken);
            }
        } catch (IOException e) {
            logger.error("Failed to purchase {}", app.getName());
        }

        return null;
    }
    private AndroidAppDeliveryData delivery(AndroidApp app, GooglePlayAPI api, String token){
        DeliveryResponse deliveryResponse;
        try{
           deliveryResponse =
                   api.delivery(app.getPackageName(), 0, app.getVersion(), 1, token);

            if (deliveryResponse.hasAppDeliveryData()
                    && deliveryResponse.getAppDeliveryData().hasDownloadUrl()) {
                return deliveryResponse.getAppDeliveryData();
            }

        } catch (IOException e) {
            logger.error("Failed to get delivery data {}", app.getName());
        }

        return null;
    }

    private StoreWorker getInstance(){
            try {
                return workers.borrowObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new PlayStoreWorker(null, null , null);
        }

    @Override
    public AppDetails getAppDetails(String packageName) {
        return null;
    }

    @Override
    public Integer getLatestVersion() {
        return null;
    }

    @Override
    public int getNumberOfInstances() {
        return 0;
    }

    @Override
    public void spoof(VirtualDevice device) {

    }

    @Override
    public void login(String username, String password) {

    }

    @Override
    public void login(Map<String, String> credentials) {

    }
}
