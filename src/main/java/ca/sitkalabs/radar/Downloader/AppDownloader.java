package ca.sitkalabs.radar.Downloader;

import ca.sitkalabs.radar.AppStores.GooglePlayStore;
import ca.sitkalabs.radar.AppStores.StoreAPI;
import ca.sitkalabs.radar.Models.app.AndroidApp;
import ca.sitkalabs.radar.Models.app.iOSApp;

public class AppDownloader implements Downloader{

    private StoreAPI googlePlay;
    private StoreAPI appStore;

    public AppDownloader(StoreAPI googlePlay, StoreAPI appStore){
        this.googlePlay = googlePlay;
        this.appStore = appStore;
    }

    @Override
    public void download(AndroidApp app) {

    }

    @Override
    public void download(iOSApp app) {

    }
}
