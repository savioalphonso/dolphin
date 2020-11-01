package ca.sitkalabs.radar.Models.store;

import ca.sitkalabs.radar.GooglePlayStore.api.PlayStoreAPIFactory;
import ca.sitkalabs.radar.Models.device.VirtualDevice;
import com.dragons.aurora.playstoreapiv2.ApiBuilderException;
import com.dragons.aurora.playstoreapiv2.GooglePlayAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PlayStoreWorker extends StoreWorker{

    private static int id = 0;
    private final int workerId;
    private String gsfId;
    private String aasToken;
    private String authToken;
    private GooglePlayAPI api;
    private VirtualDevice device;
    private Logger logger;


    public PlayStoreWorker(String email, String password, VirtualDevice device){
        super(email, password);
        this.device = device;
        this.workerId = id;
        id++;
        logger = LoggerFactory.getLogger(getClass());
    }

    private void setupPlayAPI() throws IOException, ApiBuilderException {

        this.api = PlayStoreAPIFactory.getAPIByUserName(email, password, device);
        authToken = this.api.getToken();
        gsfId = this.api.getGsfId();
    }

    @Override
    public boolean authenticate(){
        isAuthenticated = false;

        if (!valueExists(aasToken)){
            aasToken = getAasToken(this.email, this.password);
            isAuthenticated = createNewInstance();
        } else if (valueExists(gsfId)){
            isAuthenticated = reauthenticate();
        }

        return isAuthenticated;
    }

    @Override
    public boolean spoofDevice(VirtualDevice device) {
        this.device = device;
        //TODO: spoof
        return false;
    }

    private boolean createNewInstance() {
        try {
            setupPlayAPI();
            return true;
        } catch (IOException | ApiBuilderException e) {
            logger.error("Worker {} ({}) failed to login: {}", workerId, email, e.getMessage());
            logger.error("", e);
            return false;
        }
    }

    private boolean reauthenticate() {
        try{
            this.api = PlayStoreAPIFactory.getAPI(gsfId, authToken, device);
            return true;
        } catch (ApiBuilderException | IOException e) {
            return false;
        }

    }

    private boolean valueExists(String value){
        return value != null && value.length() > 0;
    }

    private String getAasToken(String email, String password) {
        return null;
    }

    public int getId() {
        return workerId;
    }

    public GooglePlayAPI getApi() {
        return api;
    }
}
