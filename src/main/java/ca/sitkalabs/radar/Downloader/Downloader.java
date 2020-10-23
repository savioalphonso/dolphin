package ca.sitkalabs.radar.Downloader;

import ca.sitkalabs.radar.Models.app.AndroidApp;
import ca.sitkalabs.radar.Models.app.iOSApp;

import java.util.List;

public interface Downloader {

    void download(AndroidApp app);
    void download(iOSApp app);



}
