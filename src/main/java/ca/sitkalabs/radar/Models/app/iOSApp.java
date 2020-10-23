package ca.sitkalabs.radar.Models.app;

import ca.sitkalabs.radar.Models.packages.IOSAppPackage;

import java.util.List;

public class iOSApp implements App<String>{
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getPackageName() {
        return null;
    }

    @Override
    public String getVersion() {
        return null;
    }

    @Override
    public void setNewVersion(String newVersion) {

    }

    @Override
    public List<IOSAppPackage> getAppFiles() {
        return null;
    }
}
