package ca.sitkalabs.radar.Downloader;

import ca.sitkalabs.radar.AppStores.AppleAppStore;
import ca.sitkalabs.radar.AppStores.GooglePlayStore;
import ca.sitkalabs.radar.AppStores.StoreAPI;
import ca.sitkalabs.radar.Models.app.AndroidApp;
import ca.sitkalabs.radar.Models.app.App;
import ca.sitkalabs.radar.Models.app.iOSApp;
import com.dragons.aurora.playstoreapiv2.AndroidAppDeliveryData;
import com.dragons.aurora.playstoreapiv2.GooglePlayAPI;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.image.DataBuffer;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.*;

public class DownloaderSubscriber implements Downloader, Subscriber<App<?>>{
    private GooglePlayStore googlePlay;
    private AppleAppStore appStore;
    private Subscription s;

    @Override
    public void download(AndroidApp app) {

    }

    @Override
    public void download(iOSApp app) {

    }

    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(App<?> app) {

    }

    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }

//    public DownloaderSubscriber(GooglePlayStore googlePlay, AppleAppStore appStore){
//        this.googlePlay = googlePlay;
//        this.appStore = appStore;
//    }
//
//
//    @Override
//    public void download(AndroidApp app) {
//        AndroidAppDeliveryData deliveryData = googlePlay.fetchDownloadData(app);
//        URL url = new URL(deliveryData.getDownloadUrl());
//        ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
//        FileOutputStream fileOutputStream = new FileOutputStream("/android/" + app.getPackageName() + "/" + app.getPackageName() + ".apk");
//        FileChannel fileChannel = fileOutputStream.getChannel();
//        fileOutputStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
//    }
//
//    @Override
//    public void download(iOSApp app) {
//
//    }
}
