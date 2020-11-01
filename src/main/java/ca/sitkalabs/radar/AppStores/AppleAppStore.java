package ca.sitkalabs.radar.AppStores;

import ca.sitkalabs.radar.Models.device.VirtualDevice;
import ca.sitkalabs.radar.Models.packages.AppPackageFile;
import ca.sitkalabs.radar.Models.store.AppDetails;

import java.util.List;
import java.util.Map;

public class AppleAppStore implements StoreAPI<String>{


    @Override
    public AppDetails getAppDetails(String packageName) {
        return null;
    }

    @Override
    public String getLatestVersion() {
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
