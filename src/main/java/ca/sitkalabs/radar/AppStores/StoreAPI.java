package ca.sitkalabs.radar.AppStores;

import ca.sitkalabs.radar.Models.device.Device;
import ca.sitkalabs.radar.Models.packages.AppPackageFile;
import ca.sitkalabs.radar.Models.store.AppDetails;

import java.util.List;
import java.util.Map;

public interface StoreAPI{

    List<? extends AppPackageFile> download(String packageName);
    AppDetails getAppDetails(String packageName);
    <T> T getLatestVersion();
    int getNumberOfInstances();

    void spoof(Device device);
    void login(String username, String password);
    void login(Map<String, String> credentials);

}
