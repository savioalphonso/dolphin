package ca.sitkalabs.radar.Service;

import ca.sitkalabs.radar.AppStores.StoreAPI;
import ca.sitkalabs.radar.Downloader.AppDownloader;
import ca.sitkalabs.radar.Models.app.AndroidApp;
import ca.sitkalabs.radar.Models.app.App;
import reactor.core.publisher.Flux;

public class DownloadService {

    private StoreAPI googlePlay;
    private StoreAPI appStore;

    public DownloadService(){

    }

    public downloadQueue(){

        Flux<App> queuedApps = Flux.just(new AndroidApp("Chrome", "com.android.chrome"));

        AppDownloader downloader = new AppDownloader(googlePlay, appStore);

        queuedApps.subscribe(app -> app.acceptDownloader(downloader));
    }


}
