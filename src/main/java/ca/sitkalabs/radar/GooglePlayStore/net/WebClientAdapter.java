package ca.sitkalabs.radar.GooglePlayStore.net;

import ca.sitkalabs.radar.AppStores.net.WebClientFactory;
import com.dragons.aurora.playstoreapiv2.HttpClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class WebClientAdapter extends HttpClientAdapter {

    private WebClient client;

    public WebClientAdapter(){
        client = WebClientFactory.getWebClient("Google Play");
    }

    @Override
    public byte[] get(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        client.get()
    }

    @Override
    public byte[] getEx(String url, Map<String, List<String>> params, Map<String, String> headers) throws IOException {
        return new byte[0];
    }

    @Override
    public byte[] postWithoutBody(String url, Map<String, String> urlParams, Map<String, String> headers) throws IOException {
        return new byte[0];
    }

    @Override
    public byte[] post(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        return new byte[0];
    }

    @Override
    public byte[] post(String url, byte[] body, Map<String, String> headers) throws IOException {
        return new byte[0];
    }

    @Override
    public String buildUrl(String url, Map<String, String> params) {

        HttpUr


        return null;
    }

    @Override
    public String buildUrlEx(String url, Map<String, List<String>> params) {
        return null;
    }
}
