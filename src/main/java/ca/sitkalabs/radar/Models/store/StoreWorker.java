package ca.sitkalabs.radar.Models.store;

import ca.sitkalabs.radar.AppStores.StoreAPI;
import ca.sitkalabs.radar.Models.device.VirtualDevice;

public abstract class StoreWorker {
    protected final String email;
    protected String password;
    protected boolean isAuthenticated;

    public StoreWorker(String username, String password) {
        this.email = username;
        this.password = password;
    }

    public abstract boolean authenticate();
    public abstract boolean spoofDevice(VirtualDevice device);
    public abstract <T> T getApi();
}
