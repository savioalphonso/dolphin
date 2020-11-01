package ca.sitkalabs.radar.AppStores.net;

import org.springframework.web.reactive.function.client.WebClient;

public class WebClientFactory {

    public static WebClient getWebClient(String api){
        if ("Google Play".equals(api)) {
            return GooglePlayWebClient.getClient();
        }

        return null;

    }
}
