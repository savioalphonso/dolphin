package ca.sitkalabs.radar.Service;

import ca.sitkalabs.radar.AppStores.AppleAppStore;
import ca.sitkalabs.radar.AppStores.GooglePlayStore;
import ca.sitkalabs.radar.Models.app.AndroidApp;
import ca.sitkalabs.radar.Models.app.App;
import reactor.core.publisher.Flux;

public class DownloadService {

    private GooglePlayStore googlePlay;
    private AppleAppStore appStore;

    public DownloadService(){

    }

    public void downloadQueue(){

        Flux<App> queuedApps = Flux.just(new AndroidApp("Chrome", "com.android.chrome"));

    }


}
