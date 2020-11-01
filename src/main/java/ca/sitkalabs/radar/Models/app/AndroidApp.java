package ca.sitkalabs.radar.Models.app;

import ca.sitkalabs.radar.Downloader.DownloaderSubscriber;

public class AndroidApp implements App<Integer> {

    private final String name;
    private final String packageName;
    private Integer version;
    private String versionString;
    private boolean isNative;


    public AndroidApp(String name, String packageName){
        this.name = name;
        this.packageName = packageName;
    }

    public AndroidApp(AndroidApp app){
        this.name = app.name;
        this.packageName = app.packageName;
        this.version = app.version;
        this.versionString = app.versionString;
        this.isNative = app.isNative;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setNewVersion(Integer newVersion) {
        this.version = newVersion;
    }

    @Override
    public void registerDownloader(DownloaderSubscriber downloader) {
        downloader.download(this);
    }

    public void setNewVersionString(String versionString){
        this.versionString = versionString;
    }

    public String getVersionString(){
        return versionString;
    }

    public boolean isNative() {
        return isNative;
    }

    public void setNative(boolean isNative) {
        this.isNative = isNative;
    }
}
