package ca.sitkalabs.radar.Models.packages;

public interface AppPackageFile {

    String getExtension();
    String getVersionString();
    int getVersion();
    void getCompatibility();
}
