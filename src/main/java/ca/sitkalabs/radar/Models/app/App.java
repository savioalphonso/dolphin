package ca.sitkalabs.radar.Models.app;

import ca.sitkalabs.radar.Downloader.AppDownloader;

public interface App<ID>  {

    String getName();
    String getPackageName();
    ID getVersion();
    void setNewVersion(ID newVersion);
    void acceptDownloader(AppDownloader downloader);
}
