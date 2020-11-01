package ca.sitkalabs.radar.GooglePlayStore.api;

import ca.sitkalabs.radar.GooglePlayStore.net.WebClientAdapter;
import ca.sitkalabs.radar.Models.device.VirtualDevice;
import com.dragons.aurora.playstoreapiv2.ApiBuilderException;
import com.dragons.aurora.playstoreapiv2.GooglePlayAPI;
import com.dragons.aurora.playstoreapiv2.PlayStoreApiBuilder;

import java.io.IOException;

public class PlayStoreAPIFactory {
    public static GooglePlayAPI getAPIByUserName(String email, String aasToken, VirtualDevice device) throws IOException, ApiBuilderException {
        PlayStoreApiBuilder builder = new PlayStoreApiBuilder();
        builder.setEmail(email);
        builder.setAasToken(aasToken);
        builder.setHttpClient(new WebClientAdapter());
        builder.setDeviceInfoProvider(device.getProvider());
        return builder.build();
    }

    public static GooglePlayAPI getAPI(String gsfId, String authToken, VirtualDevice device) throws IOException, ApiBuilderException {
        PlayStoreApiBuilder builder = new PlayStoreApiBuilder();
        builder.setGsfId(gsfId);
        builder.setAuthToken(authToken);
        builder.setHttpClient(new WebClientAdapter());
        builder.setDeviceInfoProvider(device.getProvider());
        return builder.build();
    }
}
