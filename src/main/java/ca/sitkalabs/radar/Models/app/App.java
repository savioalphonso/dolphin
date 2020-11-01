package ca.sitkalabs.radar.Models.app;

import ca.sitkalabs.radar.Downloader.DownloaderSubscriber;

public interface App<ID>  {

    String getName();
    String getPackageName();
    ID getVersion();
    void setNewVersion(ID newVersion);
    void registerDownloader(DownloaderSubscriber downloader);
}
