package ca.sitkalabs.radar.AppStores;

import ca.sitkalabs.radar.Models.app.App;
import ca.sitkalabs.radar.Models.device.VirtualDevice;
import ca.sitkalabs.radar.Models.packages.AppPackageFile;
import ca.sitkalabs.radar.Models.store.AppDetails;

import java.util.List;
import java.util.Map;

public interface StoreAPI<T>{

    AppDetails getAppDetails(String packageName);
    T getLatestVersion();
    int getNumberOfInstances();

    void spoof(VirtualDevice device);
    void login(String username, String password);
    void login(Map<String, String> credentials);

}
